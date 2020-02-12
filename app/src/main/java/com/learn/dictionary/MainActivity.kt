package com.learn.dictionary

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSearchView()
    }

    private fun setSearchView() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchableInfo = searchManager.getSearchableInfo(componentName)

        searchView.setSearchableInfo(searchableInfo)
    }
}
