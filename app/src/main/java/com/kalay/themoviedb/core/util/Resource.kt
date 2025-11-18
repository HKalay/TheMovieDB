package com.kalay.themoviedb.core.util

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Resource<out T> {

    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: ErrorResponse) : Resource<T>()
    object Loading : Resource<Nothing>()
    object Empty : Resource<Nothing>()

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> error(error: ErrorResponse): Resource<T> = Error(error)
        fun <T> loading(): Resource<T> = Loading
        fun <T> empty(): Resource<T> = Empty
    }
}

data class ErrorResponse(
    val code: Int? = null,
    val type: String? = null,
    val message: String? = null
)

fun Throwable.toErrorResult(): Resource.Error<Nothing> {
    val errorResponse = when (this) {
        is HttpException -> {
            val code = this.code()
            val message = try {
                this.response()?.errorBody()?.string()
            } catch (e: Exception) {
                "HTTP Error"
            }
            ErrorResponse(code, "HttpException", message)
        }

        is SocketTimeoutException -> ErrorResponse(null, "Timeout", "The connection timed out.")
        is UnknownHostException -> ErrorResponse(null, "NoInternet", "Check your internet connection.")
        is IOException -> ErrorResponse(null, "NetworkError", "A network error has occurred.")
        is IllegalArgumentException -> ErrorResponse(null, "IllegalArgument", message)
        else -> ErrorResponse(null, "UnknownError", message ?: "An unknown error has occurred.")
    }

    return Resource.Error(errorResponse)
}

