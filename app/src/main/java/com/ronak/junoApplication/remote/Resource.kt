package com.ronak.junoApplication.remote

open class Resource<T>(
    val state: State? = null,
    val data: T? = null,
    val error: String? = null,
) {
    class Success<T>(data: T) : Resource<T>(State.SUCCESS, data)
    class Loading<T>(data: T? = null) : Resource<T>(State.LOADING, data)
    class DataError<T>(error: String) : Resource<T>(State.ERROR, null, error)
}

enum class State {
    SUCCESS, LOADING, ERROR
}