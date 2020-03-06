package com.learn.dictionary.dagger

import android.util.Log.VERBOSE
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.learn.dictionary.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("loggingInterceptor")
    fun providesHttpLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .log(VERBOSE)
            .addHeader("X-RapidAPI-Key", "8b69a3f1ccmsh37d468b98bc08a0p16a47cjsnbf74ecf29a48")
            .addHeader("X-RapidAPI-Host", "mashape-community-urban-dictionary.p.rapidapi.com")
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        @Named("loggingInterceptor") loggingInterceptor: LoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        val baseURL = "https://mashape-community-urban-dictionary.p.rapidapi.com/"

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
    }

    @Singleton
    @Provides
    fun providesApiService(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder.build().create(ApiService::class.java)
    }

}