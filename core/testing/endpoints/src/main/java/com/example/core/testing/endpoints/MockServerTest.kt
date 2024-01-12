package com.example.core.testing.endpoints

import com.example.core.networking.enum.EnumConverterFactory
import com.example.core.networking.factory.ResCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

open class MockServerTest {

    val server = MockWebServer()

    protected inline fun <reified T : Any> buildApi(): T {
        val client = OkHttpClient.Builder().apply {
            callTimeout(1.seconds.toJavaDuration())
            connectTimeout(1.seconds.toJavaDuration())
            writeTimeout(1.seconds.toJavaDuration())
        }.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(ResCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .build()
            .create(T::class.java)
    }
}

fun MockWebServer.enqueueJsonResponse(
    code: Int = 200,
    jsonFile: String,
) {
    enqueue(MockResponse().setResponseCode(code).setJsonBody(jsonFile))
}

fun MockWebServer.enqueueJsonResponse(jsonFile: String) {
    enqueue(MockResponse().setResponseCode(200).setJsonBody(jsonFile))
}

fun MockWebServer.enqueueOKResponse() = enqueueResponse(200)

fun MockWebServer.enqueueResponse(statusCode: Int) =
    enqueue(MockResponse().setResponseCode(statusCode))

fun MockWebServer.dispatch(dispatch: (RecordedRequest) -> MockResponse) {
    dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest) = dispatch(request)
    }
}
