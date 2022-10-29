package com.ronak.junoApplication

import com.ronak.junoApplication.remote.RetrofitService

class ApiRepository {
    private var apiService = RetrofitService.service

    suspend fun getEmptyValues() = apiService.getEmptyValues()

    suspend fun getValues() = apiService.getValues()
}