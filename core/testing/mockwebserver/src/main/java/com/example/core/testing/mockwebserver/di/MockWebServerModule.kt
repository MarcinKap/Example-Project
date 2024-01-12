package com.example.core.testing.mockwebserver.di

import com.example.core.testing.mockwebserver.extensions.defaults
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.concurrent.thread
import okhttp3.mockwebserver.MockWebServer
import pl.droidsonroids.testing.mockwebserver.FixtureDispatcher

@Qualifier
annotation class MockWebServerUrl

@Module
@InstallIn(SingletonComponent::class)
object MockWebServerModule {

    @Provides
    @Singleton
    fun provideMockWebServerDispatcher(): FixtureDispatcher = FixtureDispatcher().apply {
        defaults()
    }

    @Provides
    @Singleton
    fun provideMockWebServer(dispatcher: FixtureDispatcher): MockWebServer = MockWebServer().apply {
        this.dispatcher = dispatcher
    }

    @MockWebServerUrl
    @Provides
    @Singleton
    fun getMockWebServerUrl(mockWebServer: MockWebServer): String {
        var mockWebServerPath: String? = null
        thread(start = true) {
            mockWebServerPath = mockWebServer
                .url("/")
                .toString()
        }.join()

        return checkNotNull(mockWebServerPath) {
            "Mock server path could not be resolved. There might be a problem starting the server."
        }
    }
}
