package com.example.data.product.datasource.remote.product

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.model.remote.ProductPageResponse

interface ProductRemoteDataSource {

    suspend fun getProductPage(): Res<NetworkError, ProductPageResponse>
}
