package com.example.data.product.repository

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.datasource.remote.product.ProductRemoteDataSource
import com.example.data.product.mapper.toProductPage
import com.example.data.product.model.internal.ProductPage
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
) : ProductRepository {
    override suspend fun getProducts(): Res<NetworkError, ProductPage> {
        return remoteDataSource.getProductPage().map {
            it.toProductPage()
        }
    }
}
