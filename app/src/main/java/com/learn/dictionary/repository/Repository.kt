package com.learn.dictionary.repository

import com.learn.dictionary.api.ApiService
import com.learn.dictionary.model.ApiResult
import kotlinx.coroutines.CompletableJob
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val apiService: ApiService) {

    var job: CompletableJob? = null

    suspend fun getSynonym(key: String): ApiResult {
        return apiService.getSynonyms(key)
    }

    fun cancelJobs() = job?.cancel()
}