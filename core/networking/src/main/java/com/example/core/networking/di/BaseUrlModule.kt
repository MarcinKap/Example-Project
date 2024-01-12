package com.example.core.networking.di

import com.example.core.networking.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object BaseUrlModule {

    @Provides
    @ApiUrl
    fun provideApiUrl(): String = BuildConfig.BASE_API_URL
}
