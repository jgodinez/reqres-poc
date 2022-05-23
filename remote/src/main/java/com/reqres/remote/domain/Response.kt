package com.reqres.remote.domain

sealed class Response<out T> {
    data class Success<out T>(val response: T) : Response<T>()

    data class Failure(val error: Exception) : Response<Nothing>()

    val isSuccess get() = this is Success<T>

    val isError get() = this is Failure

    fun fold(onSuccess: (T) -> Unit, onFailure: (Exception) -> Unit = {}): Any =
        when (this) {
            is Success -> onSuccess(response)
            is Failure -> onFailure(error)
        }

    suspend fun subscribe(
        onSuccess: suspend (T) -> Unit = {},
        onFailure: suspend (Exception) -> Unit = {}
    ): Any =
        when (this) {
            is Success -> onSuccess(response)
            is Failure -> onFailure(error)
        }
}