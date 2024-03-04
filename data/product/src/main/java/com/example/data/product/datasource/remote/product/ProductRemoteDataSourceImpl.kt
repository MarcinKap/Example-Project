package com.example.data.product.datasource.remote.product

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.endpoint.ProductEndpoint
import com.example.data.product.model.remote.ProductPageResponse
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val productEndpoint: ProductEndpoint,
) :
    ProductRemoteDataSource {
    override suspend fun getProductPage(): Res<NetworkError, ProductPageResponse> {
        return productEndpoint.getAllProducts()
    }

    override suspend fun getProductPageByCategory(category: String): Res<NetworkError, ProductPageResponse> {
        return productEndpoint.getProductsByCategory(category)
    }

    override suspend fun getCategories(): Res<NetworkError, List<String>> {
        return productEndpoint.getCategories()
    }
}
