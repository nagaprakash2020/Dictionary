package com.learn.dictionary.dagger

import com.learn.dictionary.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("loggingInterceptor")
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    @Named("authInterceptor")
    fun providesAuthenticationInterceptor(): Interceptor {

        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("X-RapidAPI-Key", "8b69a3f1ccmsh37d468b98bc08a0p16a47cjsnbf74ecf29a48")
                .header("X-RapidAPI-Host", "mashape-community-urban-dictionary.p.rapidapi.com")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        @Named("loggingInterceptor") loggingInterceptor: HttpLoggingInterceptor,
        @Named("authInterceptor") authenticationInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
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