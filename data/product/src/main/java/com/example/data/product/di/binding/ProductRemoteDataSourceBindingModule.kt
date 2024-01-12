package com.example.data.product.di.binding

import com.example.data.product.datasource.remote.product.ProductRemoteDataSource
import com.example.data.product.datasource.remote.product.ProductRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface ProductRemoteDataSourceBindingModule {
    @Binds
    fun bindProductRemoteDataSource(
        productRemoteDataSourceImpl: ProductRemoteDataSourceImpl,
    ): ProductRemoteDataSource
}
