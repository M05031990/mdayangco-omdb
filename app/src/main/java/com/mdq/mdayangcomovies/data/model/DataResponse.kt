package com.mdq.mdayangcomovies.data.model

sealed class DataResponse<out T: Any> {
    data class Success<T: Any>(val data: T): DataResponse<T>()
    data class Error(val throwable: Throwable): DataResponse<Nothing>()
}
