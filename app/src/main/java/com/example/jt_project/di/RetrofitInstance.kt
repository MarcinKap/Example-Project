package com.example.jt_project.di

import android.content.Context
import com.example.jt_project.api.RetrofitApi
import com.example.jt_project.api.repositories.PostListRepository
import com.example.jt_project.api.repositories.PostListRepositoryImpl
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

    @Provides
    @Singleton
    fun providePostListRepository(
        api: RetrofitApi
    ): PostListRepository {
        return PostListRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance() : RetrofitApi {

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
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(RetrofitApi::class.java);
    }

}