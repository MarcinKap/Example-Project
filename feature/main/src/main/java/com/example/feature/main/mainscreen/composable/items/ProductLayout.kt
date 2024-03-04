package com.example.feature.main.mainscreen.composable.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.core.design.spacer.VerticalSpacer
import com.example.core.design.theme.ExampleTheme
import com.example.core.design.theme.Grey30
import com.example.core.design.theme.Red50
import com.example.core.design.theme.Yellow80
import com.example.feature.main.mainscreen.composable.previewData.product_1
import com.example.feature.main.mainscreen.model.ProductMain
import com.example.core.design.R as D

internal fun LazyListScope.productsLayout(products: List<ProductMain>) {
    val chunkedProducts = products.chunked(2)

    chunkedProducts.forEach {
        val firstProduct = it.getOrNull(0)
        val secondProduct = it.getOrNull(1)

        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Product(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    product = firstProduct,
                )
                Product(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight() .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    product = secondProduct,
                )
            }
        }
    }
}

@Composable
private fun Product(
    product: ProductMain?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ProductImage(url = product?.thumbnail)
        Spacer(modifier = Modifier.height(4.dp))
        ProductCategory(value = product?.category)
        VerticalSpacer(height = 4.dp)
        ProductTitle(value = product?.title)
        VerticalSpacer(height = 8.dp)
        Spacer(modifier = Modifier.weight(1f))
        ProductSummary(
            rating = product?.rating,
            price = product?.price,
            discountPercentage = product?.discountPercentage,
        )
    }
}

@Composable
private fun ProductImage(url: String?) {
    Box(
        modifier = Modifier.height(150.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Image(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            painter = rememberAsyncImagePainter(
                model = url,
                placeholder = painterResource(id = D.drawable.photo_placeholder),
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun ProductCategory(value: String?) {
    Text(
        text = value ?: "",
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
private fun ProductTitle(value: String?) {
    Text(
        text = value ?: "",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
    )
}

@Composable
private fun ProductSummary(
    rating: Double?,
    price: Int?,
    discountPercentage: Double?,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            rating?.let {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(id = android.R.drawable.btn_star_big_on),
                        contentDescription = null,
                        tint = Yellow80,
                    )
                    Text(
                        text = it.toString(),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }

            }
        }

        val discount = if (discountPercentage != null && discountPercentage > 0) {
            " (-${discountPercentage.toInt()}%)"
        } else {
            ""
        }
        val productPrize = "${price}zł$discount"

        Text(
            text = productPrize,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (discount.isNotBlank()) {
                Red50
            } else {
                Grey30
            },
        )
    }
}

@Composable
private fun ProductDescription(
    product: ProductMain,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {

        VerticalSpacer(height = 4.dp)
        Text(
            text = product.title ?: "",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
        )

        VerticalSpacer(height = 8.dp)


    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDescriptionPreview() {
    ExampleTheme {
        ProductDescription(product = product_1)
    }
}