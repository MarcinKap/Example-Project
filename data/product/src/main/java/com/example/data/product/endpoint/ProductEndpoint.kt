package com.example.data.product.endpoint

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.model.remote.ProductPageResponse
import retrofit2.http.GET

interface ProductEndpoint {
    @GET("/products")
    suspend fun getAllProducts(): Res<NetworkError, ProductPageResponse>

    @GET("/products/categories")
    suspend fun getCategories(): Res<NetworkError, List<String>>
}
