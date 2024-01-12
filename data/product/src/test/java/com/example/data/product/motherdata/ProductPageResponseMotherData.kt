package com.example.data.product.motherdata

import com.example.data.product.model.remote.ProductPageResponse
import com.example.data.product.model.remote.ProductResponse

object ProductPageResponseMotherData {

    private val product = ProductResponse(
        id = 1,
        title = "iPhone 9",
        description = "An apple mobile which is nothing like apple",
        price = 549,
        discountPercentage = 12.96,
        rating = 4.69,
        stock = 94,
        brand = "Apple",
        category = "smartphones",
        thumbnail = "https://example.com/thumbnail.jpg", // Przykładowy link do obrazka
        images = arrayListOf(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg",
        ),
    )

    val productPageResponse = ProductPageResponse(
        products = arrayListOf(product),
        total = 100,
        skip = 0,
        limit = 30,
    )
}
