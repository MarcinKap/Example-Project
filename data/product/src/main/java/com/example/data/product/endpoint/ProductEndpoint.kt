package com.example.data.product.endpoint

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.model.remote.ProductPageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductEndpoint {
    @GET("/products")
    suspend fun getAllProducts(): Res<NetworkError, ProductPageResponse>

    @GET("/products/category/{category_name}")
    suspend fun getProductsByCategory(
        @Path("category_name") category: String,
    ): Res<NetworkError, ProductPageResponse>

    @GET("/products/categories")
    suspend fun getCategories(): Res<NetworkError, List<String>>
}
