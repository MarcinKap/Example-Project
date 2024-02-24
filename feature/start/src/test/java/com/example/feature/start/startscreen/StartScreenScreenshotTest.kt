package com.example.feature.start.startscreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.core.testing.screenshots.PaparazziTest
import com.example.feature.start.startscreen.composable.StartScreenContent
import org.junit.Test

class StartScreenScreenshotTest : PaparazziTest() {

    @Test
    fun StartScreenTest() = test {
        Surface {
            StartScreenContent{}
        }
    }

    private fun test(content: @Composable () -> Unit) = paparazzi.snapshot {
        MaterialTheme {
            content()
        }
    }
}
