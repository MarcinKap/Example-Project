package com.example.core.testing.screenshots

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams

object PaparazziFactory {
    fun compose(
        deviceConfig: DeviceConfig = DeviceConfig.NEXUS_5,
    ): Paparazzi {
        val environment = detectEnvironment()
        return Paparazzi(
            environment = environment.copy(
                compileSdkVersion = 33,
                platformDir = environment.platformDir.replace("34", "33"),
            ),
            deviceConfig = deviceConfig.copy(locale = "en"),
            showSystemUi = false,
            renderingMode = SessionParams.RenderingMode.SHRINK,
        )
    }
}