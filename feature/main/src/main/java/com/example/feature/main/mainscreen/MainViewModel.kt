package com.example.feature.main.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.product.usecase.ProductPageUseCase
import com.example.feature.main.mainscreen.mapper.toProductMain
import com.jobandtalent.companies.core.coroutines.IO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productPageUseCase: ProductPageUseCase,
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

            productPageUseCase.invoke().handle(
                errorResponse = {
                    updateMainState(MainState.Error(it))
                },
                successResponse = { productPage ->
                    _uiState.update {
                        it.copy(
                            state = MainState.Success,
                            products = productPage.products.map { it.toProductMain() },
                        )
                    }
                },
            )
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
