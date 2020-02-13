package com.learn.dictionary

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun runThisBeforeEveryTest(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun is_searchViewVisible(){
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
    }

    @Test
    fun can_type_text_in_searchView(){
        onView(withId(R.id.searchView)).perform(click(),typeSearchViewText("vitamin"))
    }

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change Search View"
            }

            override fun perform(uiController: UiController, view: View) {
                (view as SearchView).setQuery(text, true)
            }
        }
    }
}