package com.learn.dictionary.synonyms

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learn.dictionary.repository.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*

@RunWith(MockitoJUnitRunner::class)
class SynonymsViewModelTest {

    lateinit var viewModel: SynonymsViewModel
    @Mock lateinit var repository: Repository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onSetup() {
        MockitoAnnotations.initMocks(this)
        viewModel = SynonymsViewModel(repository)
    }

    @Test
    fun setSearchString_sets_searchString() {

        val mockString = "Hello"
        viewModel.setSearchString(mockString)

        assertEquals(viewModel.search.value, mockString)
    }
}