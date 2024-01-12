package com.example.core.networking.model

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class ResCall<R>(
    private val delegate: Call<R>,
    private val successType: Type,
) : Call<Res<NetworkError, R>> {
    override fun enqueue(callback: Callback<Res<NetworkError, R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@ResCall, Response.success(response.toRes()))
            }

            private fun Response<R>.toRes(): Res<NetworkError, R> {
                if (!isSuccessful) {
                    val error = mapHttpError(code(), errorBody())
                    return Res.Error(error)
                }

                val body = body()
                return when {
                    body != null -> Res.Success(body)
                    successType == Unit::class.java -> {
                        @Suppress("UNCHECKED_CAST")
                        Res.Success(Unit) as Res<NetworkError, R>
                    }

                    else -> Res.Error(NetworkError.NoBodyError)
                }
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> NetworkError.ConnectionError
                    else -> NetworkError.UnknownError
                }
                callback.onResponse(this@ResCall, Response.success(Res.Error(error)))
            }
        },
    )

    private fun mapHttpError(
        code: Int,
        errorBody: ResponseBody?,
    ): NetworkError.HttpError =
        NetworkError.HttpError(code, errorBody?.string().orEmpty())

    override fun clone(): Call<Res<NetworkError, R>> =
        ResCall(delegate.clone(), successType)

    override fun execute(): Response<Res<NetworkError, R>> = throw NotImplementedError()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
