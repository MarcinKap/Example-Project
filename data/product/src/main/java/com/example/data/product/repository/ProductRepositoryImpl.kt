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
        return remoteDataSource
            .getProductPage()
            .map { it.toProductPage() }
    }

    override suspend fun getProductsByCategory(category: String): Res<NetworkError, ProductPage> {
        return remoteDataSource
            .getProductPageByCategory(category)
            .map { it.toProductPage() }
    }

    override suspend fun getProductsByName(name: String): Res<NetworkError, ProductPage> {
        return remoteDataSource
            .getProductPageByName(name)
            .map { it.toProductPage() }
    }

    override suspend fun getCategories(): Res<NetworkError, List<String>> {
        return remoteDataSource.getCategories()
    }
}
