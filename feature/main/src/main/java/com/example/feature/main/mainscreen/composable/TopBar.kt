package com.example.feature.main.mainscreen.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.design.R
import com.example.core.design.iconbutton.IconButton
import com.example.core.design.searchbar.SearchBar
import com.example.core.design.theme.ExampleTheme

@Composable
internal fun TopBar(
    isSearchingMode: Boolean,
    onFocusSearchBar: (Boolean) -> Unit,
    onSearchBarValueChange: (String) -> Unit,
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
                Spacer(modifier = Modifier.width(8.dp))
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
                onSearchBarValueChange(it)
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
            onSearchBarValueChange = {},
            onBackPressed = {},
        )
    }
}
