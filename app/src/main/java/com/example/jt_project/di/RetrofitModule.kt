package com.example.jt_project.di

import com.example.jt_project.api.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInstance {

    val baseURL = "https://dummyapi.io/data/v1/"

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): RetrofitApi {
        return retrofit.create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("app-id", "62386e2b681a0f2b69d2ad41")
                .build()
            chain.proceed(request)
        }
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

}