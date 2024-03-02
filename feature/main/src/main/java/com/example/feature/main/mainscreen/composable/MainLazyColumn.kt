package com.example.feature.main.mainscreen.composable

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feature.main.mainscreen.composable.items.categoriesLayout
import com.example.feature.main.mainscreen.composable.items.productsLayout
import com.example.feature.main.mainscreen.model.ProductMain

@Composable
internal fun ColumnScope.MainLazyColumn(
    products: List<ProductMain>,
    categories: List<String>,
) {
    LazyColumn(
        modifier = Modifier.weight(1f),
    ) {
        if (categories.isNotEmpty()) {
            categoriesLayout(
                categories = categories,
                onCategorySelect = {},
            )
        }
        if (products.isNotEmpty()) {
            productsLayout(products)
        }
    }
}
