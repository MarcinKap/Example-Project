package com.example.data.product.di

import com.example.data.product.endpoint.ProductEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ProductEndpointModule {
    @Provides
    fun provideProductEndpoint(
        retrofit: Retrofit,
    ): ProductEndpoint =
        retrofit.create(ProductEndpoint::class.java)
}
