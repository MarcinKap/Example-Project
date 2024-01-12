package com.example.core.networking.model

sealed class NetworkError {
    data class HttpError(
        val code: Int,
        val errorBody: String = "",
    ) : NetworkError()

    data object ConnectionError : NetworkError()
    data object NoBodyError : NetworkError()
    data object UnknownError : NetworkError()
}
