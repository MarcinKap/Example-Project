package com.example.data.product.usecase

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.model.internal.ProductPage
import com.example.data.product.repository.ProductRepository
import java.time.LocalDate
import javax.inject.Inject

class ProductPageUseCase @Inject constructor(private val productRepository: ProductRepository) {

    data class Params(
        val value: String,
        val type: ProductParam,
    )

    suspend fun invoke(params: Params? = null): Res<NetworkError, ProductPage> {
        return when (params?.type) {
            ProductParam.Category -> productRepository.getProductsByCategory(params.value)
            ProductParam.Name -> productRepository.getProductsByName(params.value)
            else -> productRepository.getProducts()
        }
    }
}

sealed class ProductParam {
    object Category : ProductParam()
    object Name : ProductParam()
}