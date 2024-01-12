package com.example.data.product.repository

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.model.internal.ProductPage

interface ProductRepository {
    suspend fun getProducts(): Res<NetworkError, ProductPage>
}
