package com.example.core.networking.factory

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.core.networking.adapter.ResCallAdapter
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResCallAdapterFactory : CallAdapter.Factory() {

    @Suppress("ReturnCount")
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) { "Return type must be a parameterized type." }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != Res::class.java) return null
        check(responseType is ParameterizedType) { "Response type must be a parameterized type." }

        val errorType = getParameterUpperBound(0, responseType)
        if (getRawType(errorType) != NetworkError::class.java) return null

        val resultType = getParameterUpperBound(1, responseType)
        return ResCallAdapter<Any>(resultType)
    }
}
