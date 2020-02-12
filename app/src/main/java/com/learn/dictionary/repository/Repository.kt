package com.learn.dictionary.repository

import androidx.lifecycle.LiveData
import com.learn.dictionary.api.ApiService
import com.learn.dictionary.model.ApiResult
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val apiService: ApiService) {

    var job: CompletableJob? = null

    fun getSynonym(key: String): LiveData<ApiResult> {
        job = Job()
        return object : LiveData<ApiResult>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(Dispatchers.IO + it).launch {
                        val apiResult = apiService.getSynonyms(key)
                        withContext(Dispatchers.Main) {
                            value = apiResult
                            it.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() = job?.cancel()
}