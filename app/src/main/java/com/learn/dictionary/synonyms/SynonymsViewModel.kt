package com.learn.dictionary.synonyms

import androidx.lifecycle.*
import com.learn.dictionary.model.ApiResult
import com.learn.dictionary.repository.Repository
import javax.inject.Inject

class SynonymsViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val search: MutableLiveData<String> = MutableLiveData()
    val synonymsList: LiveData<ApiResult> = Transformations.switchMap(search) {
        liveData {
            emit(repository.getSynonym(it))
        }
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