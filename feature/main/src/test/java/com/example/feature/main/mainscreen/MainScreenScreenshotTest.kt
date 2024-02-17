package com.example.feature.main.mainscreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.core.testing.screenshots.PaparazziTest
import com.example.feature.main.mainscreen.composable.MainScreenContent
import com.example.feature.main.mainscreen.composable.previewData.previewProductList
import com.google.testing.junit.testparameterinjector.TestParameter
import org.junit.Test

class MainScreenScreenshotTest : PaparazziTest() {

    @Test
    fun MainScreenTest() = paparazzi.snapshot {
        Surface {
            MainScreenContent(
                isSearchingMode = false,
                products = previewProductList,
                onFocusSearchBar = {},
                onBackPressed = {},
            )
        }
    }

    private fun test(content: @Composable () -> Unit) = paparazzi.snapshot {
        MaterialTheme {
            content()
        }
    }
}