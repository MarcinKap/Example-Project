package com.example.core.networking.adapter

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.core.networking.model.ResCall
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResCallAdapter<R>(
    private val successType: Type,
) : CallAdapter<R, Call<Res<NetworkError, R>>> {

    override fun adapt(call: Call<R>): Call<Res<NetworkError, R>> = ResCall(call, successType)

    override fun responseType(): Type = successType
}
