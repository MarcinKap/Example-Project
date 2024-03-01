package com.example.feature.main.mainscreen.composable

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.design.R
import com.example.core.design.iconbutton.IconButton
import com.example.core.design.searchbar.SearchBar
import com.example.core.design.theme.ExampleTheme
import com.example.feature.main.mainscreen.MainNavigationEvent
import com.example.feature.main.mainscreen.MainNavigator
import com.example.feature.main.mainscreen.MainViewModel
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
        categories = listOf("All", "smartphones", "laptops", "fragrances", "skincare", "groceries"),
        isSearchingMode = uiState.isSearchingMode,
        onFocusSearchBar = viewModel::onFocusSearchBar,
        onBackPressed = viewModel::onBackPressed,
    )

    BackHandler(onBack = viewModel::onBackPressed)
}

@Composable
internal fun MainScreenContent(
    isSearchingMode: Boolean,
    products: List<ProductMain>,
    categories: List<String>,
    onFocusSearchBar: (Boolean) -> Unit,
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopBar(
            isSearchingMode = isSearchingMode,
            onFocusSearchBar = onFocusSearchBar,
            onBackPressed = onBackPressed,
        )

        AnimatedContent(isSearchingMode, label = "") { searchingMode ->
            if (searchingMode) {
                SearchedProductsLazuColumn()
            } else {
                MainLazyColumn(
                    products = products,
                    categories = categories,
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
            onFocusSearchBar = {},
            onBackPressed = {},
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun TopBar(
    isSearchingMode: Boolean,
    onFocusSearchBar: (Boolean) -> Unit,
    onBackPressed: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var value by remember { mutableStateOf("") }

    var isSearchBarFocused by remember { mutableStateOf(false) }
    val currentIsSearchingMode by rememberUpdatedState(newValue = isSearchingMode)

    LaunchedEffect(currentIsSearchingMode) {
        if (!currentIsSearchingMode) {
            value = ""
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(key1 = isSearchBarFocused) {
        if (isSearchBarFocused) {
            onFocusSearchBar(true)
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 4.dp,
                end = 4.dp,
            )
            .height(64.dp),
    ) {
        AnimatedContent(
            targetState = currentIsSearchingMode,
            label = "back button animated content",
        ) { isSearchingMode ->
            if (isSearchingMode) {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    size = 24.dp,
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    onClick = onBackPressed,
                )
            } else {
                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        SearchBar(
            value = value,
            onValueChange = { value = it },
            placeholder = "Search",
            modifier = Modifier
                .weight(1f)
                .onFocusChanged {
                    isSearchBarFocused = it.hasFocus
                },
            onSearch = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        )

        AnimatedContent(
            targetState = currentIsSearchingMode,
            label = "shop cart animated content",
        ) { isSearchingMode ->
            if (!isSearchingMode) {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    size = 24.dp,
                    painter = painterResource(id = R.drawable.shop_cart),
                    enabled = false,
                    onClick = {},
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    ExampleTheme {
        TopBar(
            isSearchingMode = true,
            onFocusSearchBar = {},
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

@Composable
private fun BasketButton() {

}