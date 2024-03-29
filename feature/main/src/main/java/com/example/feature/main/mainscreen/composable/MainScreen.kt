package com.example.feature.main.mainscreen.composable

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.design.theme.ExampleTheme
import com.example.core.design.theme.Grey95
import com.example.feature.main.mainscreen.MainNavigationEvent
import com.example.feature.main.mainscreen.MainNavigator
import com.example.feature.main.mainscreen.MainViewModel
import com.example.feature.main.mainscreen.ProductRequest
import com.example.feature.main.mainscreen.composable.previewData.previewProductList
import com.example.feature.main.mainscreen.model.ProductMain
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MainScreen(
    navigator: MainNavigator,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navigationEvent by viewModel.navigationEventFlow.collectAsStateWithLifecycle()

    MainNavigationHandler(
        navigationEvent = navigationEvent,
        navigator = navigator,
        onConsumeNavigationEvent = viewModel::onNavigationHandled,
    )

    MainScreenContent(
        products = uiState.products,
        categories = uiState.categories,
        isSearchingMode = uiState.isSearchingMode,
        selectedCategory = uiState.selectedCategory,
        lastProductRequest = uiState.lastProductsRequest,
        onFocusSearchBar = viewModel::onFocusSearchBar,
        onSearchBarValueChange = viewModel::onSearchingFieldChange,
        onCategorySelect = viewModel::onCategorySelect,
        onBackPressed = viewModel::onBackPressed,
    )

    BackHandler(onBack = viewModel::onBackPressed)
}

@Composable
internal fun MainScreenContent(
    isSearchingMode: Boolean,
    products: List<ProductMain>,
    categories: List<String>,
    selectedCategory: String,
    lastProductRequest: ProductRequest,
    onFocusSearchBar: (Boolean) -> Unit,
    onSearchBarValueChange: (String) -> Unit,
    onCategorySelect: (String) -> Unit,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey95),
    ) {
        TopBar(
            isSearchingMode = isSearchingMode,
            onFocusSearchBar = onFocusSearchBar,
            onSearchBarValueChange = onSearchBarValueChange,
            onBackPressed = onBackPressed,
        )

        AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            targetState = isSearchingMode,
            label = "",
        ) { searchingMode ->
            if (searchingMode) {
                val productsList = if (lastProductRequest !is ProductRequest.Name) {
                    emptyList()
                } else {
                    products
                }

                SearchedProductsLazuColumn(
                    products = productsList,
                )
            } else {
                MainLazyColumn(
                    products = products,
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelect = onCategorySelect,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenContentPreview() {
    ExampleTheme {
        MainScreenContent(
            isSearchingMode = true,
            products = previewProductList,
            categories = listOf("All", "smartphones", "laptops", "fragrances", "skincare", "groceries"),
            selectedCategory = "All",
            lastProductRequest = ProductRequest.AllProducts,
            onFocusSearchBar = {},
            onSearchBarValueChange = {},
            onCategorySelect = {},
            onBackPressed = {},
        )
    }
}

@Composable
private fun MainNavigationHandler(
    navigationEvent: MainNavigationEvent,
    navigator: MainNavigator,
    onConsumeNavigationEvent: () -> Unit,
) {
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.currentState

    LaunchedEffect(key1 = navigationEvent, key2 = lifecycleState) {
        if (lifecycleState != Lifecycle.State.RESUMED) {
            return@LaunchedEffect
        }

        if (navigationEvent !is MainNavigationEvent.Idle) {
            onConsumeNavigationEvent()
        }

        when (navigationEvent) {
            MainNavigationEvent.Back -> {
                navigator.onBackPressed()
            }

            MainNavigationEvent.Idle -> {}
        }
    }
}
