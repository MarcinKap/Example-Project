package com.example.feature.main.mainscreen

import com.example.core.networking.model.NetworkError
import com.example.feature.main.mainscreen.model.ProductMain

data class MainUiState(
    val state: MainState = MainState.Init,
    val isSearchingMode: Boolean = false,
    val products: List<ProductMain> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val lastProductsRequest: ProductRequest = ProductRequest.AllProducts,
)

sealed class MainState {
    data object Init : MainState()
    data object Loading : MainState()
    data object Reloading : MainState()
    data class Error(val error: NetworkError) : MainState()
    data object Success : MainState()
    data object Empty : MainState()
}

sealed class ProductRequest {
    object AllProducts : ProductRequest()
    data class Category(val value: String) : ProductRequest()
    data class Name(val value: String) : ProductRequest()
}