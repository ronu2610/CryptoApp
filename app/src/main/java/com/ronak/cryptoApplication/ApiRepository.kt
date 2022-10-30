package com.ronak.cryptoApplication

import com.ronak.cryptoApplication.remote.RetrofitService

class ApiRepository {
    private var apiService = RetrofitService.service

    suspend fun getEmptyValues() = apiService.getEmptyValues()

    suspend fun getValues() = apiService.getValues()
}