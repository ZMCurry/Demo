package com.example.wan_android.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    var retrofitService: RetrofitService = RetrofitManager.getService(RetrofitService::class.java)

    private fun <T> getService(service: Class<T>): T {
        return create().create(service)
    }

    private fun create(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://www.wanandroid.com")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}