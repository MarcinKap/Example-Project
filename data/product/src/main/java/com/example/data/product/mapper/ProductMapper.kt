package com.example.data.product.mapper

import com.example.data.product.model.internal.Product
import com.example.data.product.model.internal.ProductPage
import com.example.data.product.model.remote.ProductPageResponse
import com.example.data.product.model.remote.ProductResponse

internal fun ProductPageResponse.toProductPage() =
    ProductPage(
        products = products.map { it.toProduct() },
        total = total,
        skip = skip,
        limit = limit,
    )

internal fun ProductResponse.toProduct(): Product =
    Product(
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
