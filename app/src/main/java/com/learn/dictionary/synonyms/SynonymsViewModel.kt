package com.learn.dictionary.synonyms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.learn.dictionary.model.ApiResult
import com.learn.dictionary.repository.Repository
import kotlinx.coroutines.*
import javax.inject.Inject

class SynonymsViewModel @Inject constructor(val repository: Repository): ViewModel() {

    val search: MutableLiveData<String> = MutableLiveData()
    val synonymsList: LiveData<ApiResult> = Transformations.switchMap(search) {
        val tempList: MutableLiveData<ApiResult> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val temp = repository.getSynonym(it)
            withContext(Dispatchers.Main){
                tempList.value = temp
            }
        }
        tempList
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