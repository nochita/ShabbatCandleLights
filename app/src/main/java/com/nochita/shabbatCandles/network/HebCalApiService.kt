package com.nochita.shabbatCandles.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HebCalApiService {

    private val apiClient = OkHttpClient().newBuilder().addInterceptor(createInterceptor()).build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl("https://www.hebcal.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

}