package com.ronak.cryptoApplication.remote

import com.ronak.cryptoApplication.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: ApiService
        get() = retrofit.create(ApiService::class.java)
}