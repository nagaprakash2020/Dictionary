package com.learn.dictionary.api

import com.learn.dictionary.model.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("define")
    suspend fun getSynonyms(@Query(value = "term") key: String): ApiResult
}