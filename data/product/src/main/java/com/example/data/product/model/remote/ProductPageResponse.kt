package com.example.data.product.model.remote

data class ProductPageResponse(
    var products: ArrayList<ProductResponse> = arrayListOf(),
    var total: Int? = null,
    var skip: Int? = null,
    var limit: Int? = null,
)
