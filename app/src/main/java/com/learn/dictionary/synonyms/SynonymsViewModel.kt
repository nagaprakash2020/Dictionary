package com.learn.dictionary.synonyms

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.learn.dictionary.model.ApiResult
import com.learn.dictionary.repository.Repository
import javax.inject.Inject

class SynonymsViewModel @Inject constructor(val repository: Repository): ViewModel() {

    val search: MutableLiveData<String> = MutableLiveData()
    val synonymsList: LiveData<ApiResult> = Transformations.switchMap(search) {
        repository.getSynonym(it)
    }

    fun blabla(){
        synonymsList.observeForever {  }
    }

    fun setSearchString(key: String) {
        if (key == search.value) {
            return
        }
        search.value = key
    }

    fun cancelJob() {
        repository.cancelJobs()
    }
}