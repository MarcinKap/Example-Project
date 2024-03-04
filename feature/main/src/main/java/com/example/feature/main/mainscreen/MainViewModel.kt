package com.example.feature.main.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.coroutines.IO
import com.example.core.networking.model.Res
import com.example.core.networking.model.error
import com.example.core.networking.model.orNull
import com.example.data.product.usecase.CategoriesUseCase
import com.example.data.product.usecase.ProductPageUseCase
import com.example.data.product.usecase.ProductParam
import com.example.feature.main.mainscreen.mapper.toProductMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productPageUseCase: ProductPageUseCase,
    private val categoriesUseCase: CategoriesUseCase,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _navigationEventFlow =
        MutableStateFlow<MainNavigationEvent>(MainNavigationEvent.Idle)
    val navigationEventFlow: StateFlow<MainNavigationEvent> = _navigationEventFlow

    init {
        viewModelScope.launch(ioDispatcher) {
            updateMainState(MainState.Loading)

            val products = async { productPageUseCase.invoke() }
            val categories = async { categoriesUseCase.invoke() }

            val productsResponse = products.await()
            val categoriesResponse = categories.await()

            _uiState.update { currentState ->
                val categoriesList = categoriesResponse.orNull()?.toMutableList()
                categoriesList?.add(0, "All")

                currentState.copy(
                    state = when {
                        productsResponse is Res.Error -> MainState.Error(productsResponse.error()!!)
                        categoriesResponse is Res.Error -> MainState.Error(categoriesResponse.error()!!)
                        else -> MainState.Success
                    },
                    products = productsResponse.map { it.products.map { it.toProductMain() } }.orNull()
                        ?: currentState.products,
                    categories = categoriesList ?: currentState.categories,
                    lastProductsRequest = ProductRequest.AllProducts,
                )
            }
        }
    }

    private fun updateMainState(state: MainState) {
        _uiState.update { it.copy(state = state) }
    }

    internal fun onFocusSearchBar(boolean: Boolean) {
        _uiState.update {
            it.copy(isSearchingMode = boolean)
        }
    }

    internal fun onCategorySelect(category: String) {
        if (_uiState.value.categories.contains(category)) {
            viewModelScope.launch {
                _uiState.update {
                    it.copy(state = MainState.Reloading)
                }

                if (category == "All") {
                    downloadAllProducts()
                } else {
                    downloadProductsByCategory(category)
                }
            }
        }
    }

    internal fun onSearchingFieldChange(value: String) {
        viewModelScope.launch {
            downloadProductsByName(value)
        }
    }

    private suspend fun downloadAllProducts() {
        productPageUseCase.invoke().handle(
            errorResponse = { networkError ->
                _uiState.update {
                    it.copy(
                        state = MainState.Error(networkError),
                    )
                }
            },
            successResponse = { productPage ->
                _uiState.update {
                    it.copy(
                        products = productPage.products.map { it.toProductMain() },
                        selectedCategory = "All",
                        lastProductsRequest = ProductRequest.AllProducts,
                    )
                }
            },
        )
    }

    private suspend fun downloadProductsByCategory(category: String) {
        productPageUseCase
            .invoke(ProductPageUseCase.Params(category, ProductParam.Category))
            .handle(
                errorResponse = { networkError ->
                    _uiState.update {
                        it.copy(
                            state = MainState.Error(networkError),
                        )
                    }
                },
                successResponse = { productPage ->
                    _uiState.update {
                        it.copy(
                            products = productPage.products.map { it.toProductMain() },
                            selectedCategory = category,
                            lastProductsRequest = ProductRequest.Category(category),
                        )
                    }
                },
            )
    }

    private suspend fun downloadProductsByName(productName: String) {
        if (uiState.value.lastProductsRequest != ProductRequest.Name(productName)) {
            productPageUseCase
                .invoke(ProductPageUseCase.Params(productName, ProductParam.Name))
                .handle(
                    errorResponse = { networkError ->
                        _uiState.update {
                            it.copy(
                                state = MainState.Error(networkError),
                            )
                        }
                    },
                    successResponse = { productPage ->
                        _uiState.update {
                            it.copy(
                                products = productPage.products.map { it.toProductMain() },
                                selectedCategory = "All",
                                lastProductsRequest = ProductRequest.Name(productName),
                            )
                        }
                    },
                )
        }
    }

    internal fun onBackPressed() {
        val uiState = uiState.value

        if (uiState.isSearchingMode) {
            viewModelScope.launch {
                if (uiState.lastProductsRequest is ProductRequest.Name) {
                    downloadAllProducts()
                }
                onFocusSearchBar(false)
            }
        } else {
            navigateTo(MainNavigationEvent.Back)
        }
    }

    private fun navigateTo(event: MainNavigationEvent) {
        _navigationEventFlow.value = event
    }

    internal fun onNavigationHandled() {
        navigateTo(MainNavigationEvent.Idle)
    }
}
