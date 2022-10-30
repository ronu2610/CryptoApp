package com.ronak.cryptoApplication.remote

import com.ronak.cryptoApplication.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("home")
    suspend fun getValues(): Response<ResponseDto?>

    @GET("empty-home")
    suspend fun getEmptyValues(): Response<ResponseDto?>
}