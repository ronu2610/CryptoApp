package com.ronak.junoapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ronak.junoapplication.dto.ResponseDto
import kotlinx.coroutines.launch

class JunoViewModel(application: Application) : AndroidViewModel(application) {

    var apiRepository = ApiRepository()
    private var _emptyResponseData = MutableLiveData<ResponseDto?>()
    private var _valuesResponseData = MutableLiveData<ResponseDto?>()
    private var _responseResource = MutableLiveData(false)

    val emptyResponseData: LiveData<ResponseDto?>
        get() = _emptyResponseData
    val valuesResponseData: LiveData<ResponseDto?>
        get() = _valuesResponseData
    val responseResource: LiveData<Boolean>
        get() = _responseResource


    fun getEmptyValues() {
        viewModelScope.launch {
            _responseResource.value = false
            val response = apiRepository.getEmptyValues()
            if (response.isSuccessful) {
                _emptyResponseData.value = response.body()
                _responseResource.value = true
            }
        }
    }

    fun getValues() {
        viewModelScope.launch {
            _responseResource.value = false
            val response = apiRepository.getValues()
            if (response.isSuccessful) {
                _valuesResponseData.value = response.body()
                _responseResource.value = true
            }
        }
    }
}