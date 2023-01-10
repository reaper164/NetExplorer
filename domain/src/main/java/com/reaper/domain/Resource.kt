package com.reaper.domain

sealed class Resource<T>(val data : T? = null, val message: String? = null, val progress : Int? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Progress<T>(data : T? = null, message: String? = null, progress : Int?) : Resource<T>(data, message, progress)
}