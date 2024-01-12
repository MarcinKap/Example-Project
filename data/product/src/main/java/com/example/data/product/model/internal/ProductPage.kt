package com.example.data.product.model.internal

data class ProductPage(
    var products: List<Product>,
    var total: Int?,
    var skip: Int?,
    var limit: Int?,
)
