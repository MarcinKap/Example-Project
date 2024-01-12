package com.example.data.product.model.remote

data class ProductResponse(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var price: Int? = null,
    var discountPercentage: Double? = null,
    var rating: Double? = null,
    var stock: Int? = null,
    var brand: String? = null,
    var category: String? = null,
    var thumbnail: String? = null,
    var images: ArrayList<String> = arrayListOf(),
)
