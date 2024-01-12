package com.example.core.networking.di

import com.example.core.networking.CoroutineDispatchers
import com.example.core.networking.enum.EnumConverterFactory
import com.example.core.networking.factory.ResCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideDispatchers(): CoroutineDispatchers = CoroutineDispatchers()

    @Provides
    fun provideRetrofit(
        @ApiUrl apiUrl: String,
    ): Retrofit {
        val okHttpClient = OkHttpClient.Builder()

        return Retrofit.Builder()
            .client(okHttpClient.build())
            .baseUrl(apiUrl)
            .addCallAdapterFactory(ResCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .build()
    }
}
