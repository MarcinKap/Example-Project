package com.example.feature.main.mainscreen.composable.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.feature.main.mainscreen.model.ProductMain
import com.example.core.design.R as D

internal fun LazyListScope.productsLayout(products: List<ProductMain>) {
    item {
        Divider()
    }
    items(items = products) { product ->
        Column {
            Product(product = product)
            Divider()
        }
    }
}

@Composable
private fun Product(product: ProductMain) {
    Row(modifier = Modifier.padding(16.dp)) {
        ProductImage(product.thumbnail ?: "")
    }
}

@Composable
private fun ProductImage(url: String) {
    Image(
        modifier = Modifier.size(100.dp),
        painter = rememberAsyncImagePainter(
            model = url,
            placeholder = painterResource(id = D.drawable.photo_placeholder),
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}
