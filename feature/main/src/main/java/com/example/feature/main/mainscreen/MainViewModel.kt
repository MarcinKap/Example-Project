package com.example.feature.main.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.coroutines.IO
import com.example.core.networking.model.Res
import com.example.core.networking.model.error
import com.example.core.networking.model.orNull
import com.example.data.product.usecase.CategoriesUseCase
import com.example.data.product.usecase.ProductPageUseCase
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

    internal fun onBackPressed() {
        val uiState = uiState.value

        if (uiState.isSearchingMode) {
            onFocusSearchBar(false)
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
