package com.example.core.testing.mockwebserver.rule

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import pl.droidsonroids.testing.mockwebserver.FixtureDispatcher

class MockWebServerRule(
    private val context: Context,
) : ExternalResource() {

    lateinit var server: MockWebServer
    lateinit var fixtureDispatcher: FixtureDispatcher

    override fun before() {
        super.before()
        with(EntryPoints.get(context, MockWebServerTestEntryPoint::class.java)) {
            server = mockWebServer.also { it.start() }
            fixtureDispatcher = dispatcher
        }
    }

    override fun after() {
        super.after()
        server.shutdown()
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MockWebServerTestEntryPoint {
        val mockWebServer: MockWebServer
        val dispatcher: FixtureDispatcher
    }
}
