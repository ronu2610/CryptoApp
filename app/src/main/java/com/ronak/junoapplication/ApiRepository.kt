package com.ronak.junoapplication

import com.ronak.junoapplication.remote.RetrofitService

class ApiRepository {
    var apiService = RetrofitService.service

    suspend fun getEmptyValues() = apiService.getEmptyValues()

    suspend fun getValues() = apiService.getValues()
}