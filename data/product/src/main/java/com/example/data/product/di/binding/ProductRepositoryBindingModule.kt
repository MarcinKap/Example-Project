package com.example.data.product.di.binding

import com.example.data.product.repository.ProductRepository
import com.example.data.product.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface ProductRepositoryBindingModule {
    @Binds
    @Singleton
    fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}
