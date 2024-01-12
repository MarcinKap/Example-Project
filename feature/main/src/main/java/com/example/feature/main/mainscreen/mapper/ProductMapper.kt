package com.example.feature.main.mainscreen.mapper

import com.example.data.product.model.internal.Product
import com.example.feature.main.mainscreen.model.ProductMain

internal fun Product.toProductMain(): ProductMain {
    return ProductMain(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images,
    )
}
