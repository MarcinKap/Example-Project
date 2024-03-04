package com.example.feature.main.mainscreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.core.testing.screenshots.PaparazziTest
import com.example.feature.main.mainscreen.composable.MainScreenContent
import com.example.feature.main.mainscreen.composable.previewData.previewProductList
import org.junit.Test

class MainScreenScreenshotTest : PaparazziTest() {

    @Test
    fun MainScreenTest() = paparazzi.snapshot {
        Surface {
            MainScreenContent(
                isSearchingMode = false,
                products = previewProductList,
                categories = listOf("All", "motorcycle", "car"),
                selectedCategory = "All",
                lastProductRequest = ProductRequest.AllProducts,
                onFocusSearchBar = {},
                onSearchBarValueChange = {},
                onCategorySelect = {},
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
