package com.example.feature.main.mainscreen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feature.main.mainscreen.composable.items.productsLayout
import com.example.feature.main.mainscreen.model.ProductMain

@Composable
internal fun ColumnScope.SearchedProductsLazuColumn(
    products: List<ProductMain>,
) {
    Box(modifier = Modifier.weight(1f)) {
        LazyColumn {
            if (products.isNotEmpty()) {
                productsLayout(products)
            }
        }
    }
}
