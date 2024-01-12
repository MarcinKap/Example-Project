package com.example.feature.main.mainscreen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ColumnScope.SearchedProductsLazuColumn() {
    Box(modifier = Modifier.weight(1f)) {
        LazyColumn {
        }
    }
}
