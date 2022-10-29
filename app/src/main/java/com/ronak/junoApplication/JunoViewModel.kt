package com.ronak.junoApplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ronak.junoApplication.dto.ResponseDto
import com.ronak.junoApplication.remote.Resource
import kotlinx.coroutines.launch

class JunoViewModel(application: Application) : AndroidViewModel(application) {

    private var apiRepository = ApiRepository()
    private var _emptyResponseData = MutableLiveData<ResponseDto?>()
    private var _valuesResponseData = MutableLiveData<ResponseDto?>()
    private var _responseResource = MutableLiveData<Resource<ResponseDto?>>()

    val emptyResponseData: LiveData<ResponseDto?>
        get() = _emptyResponseData
    val valuesResponseData: LiveData<ResponseDto?>
        get() = _valuesResponseData
    val responseResource: LiveData<Resource<ResponseDto?>>
        get() = _responseResource


    fun getEmptyValues() {
        viewModelScope.launch {
            try {
                _responseResource.value = Resource.Loading()
                val response = apiRepository.getEmptyValues()
                if (response.isSuccessful) {
                    _emptyResponseData.value = response.body()
                    _responseResource.value = Resource.Success(response.body())
                } else {
                    _responseResource.value = Resource.DataError(response.message())
                }
            } catch (ex: Exception) {
                _responseResource.value = Resource.DataError(ex.message.toString())
            }
        }
    }

    fun getValues() {
        viewModelScope.launch {
            try {
                _responseResource.value = Resource.Loading()
                val response = apiRepository.getValues()
                if (response.isSuccessful) {
                    _valuesResponseData.value = response.body()
                    _responseResource.value = Resource.Success(response.body())
                } else {
                    _responseResource.value = Resource.DataError(response.message())
                }
            } catch (ex: Exception) {
                _responseResource.value = Resource.DataError(ex.message.toString())
            }
        }
    }
}