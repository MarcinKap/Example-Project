package com.example.core.networking.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ChuckerModule {

    @Provides
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
    ): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR,
        )
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .redactHeaders(HEADER_AUTHORIZATION)
            .alwaysReadResponseBody(true)
            .build()
    }

    private const val HEADER_AUTHORIZATION = "Authorization"
}
