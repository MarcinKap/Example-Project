package com.example.data.product.usecase

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.model.internal.ProductPage
import com.example.data.product.repository.ProductRepository
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun invoke(): Res<NetworkError, List<String>> {
        return productRepository.getCategories()
    }
}
