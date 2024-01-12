package com.example.core.networking.model

sealed class Res<out Error, out Success> {
    data class Error<out Error>(val error: Error) : Res<Error, Nothing>()
    data class Success<out Success>(val result: Success) : Res<Nothing, Success>()

    inline fun <T> handle(
        errorResponse: (Error) -> T,
        successResponse: (Success) -> T,
    ): T =
        when (this) {
            is Res.Error -> errorResponse(error)
            is Res.Success -> successResponse(result)
        }

    inline fun <R> map(
        fn: (Success) -> R,
    ): Res<Error, R> =
        handle({ Error(it) }, { Success(fn(it)) })

    inline fun <E> mapError(
        fn: (Error) -> E,
    ): Res<E, Success> =
        handle({ Error(fn(it)) }, { Success(it) })
}
