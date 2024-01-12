package com.example.data.product.repository

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.datasource.remote.product.ProductRemoteDataSource
import com.example.data.product.motherdata.ProductPageMotherData.productPage
import com.example.data.product.motherdata.ProductPageResponseMotherData.productPageResponse
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Named.named
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ProductRepositoryImplTest {

    private lateinit var sut: ProductRepository
    private val productRemoteDataSource: ProductRemoteDataSource = mockk()

    @BeforeEach
    fun setUp() {
        sut = ProductRepositoryImpl(productRemoteDataSource)
    }

    @Test
    fun `Should call remote data source when getProducts is called`() =
        runTest {
            givenSuccessfulProductPageRequest()

            sut.getProducts()

            coVerify { productRemoteDataSource.getProductPage() }
        }

    @Test
    fun `Should correctly map success response when getProducts is called`() =
        runTest {
            givenSuccessfulProductPageRequest()

            val actual = sut.getProducts()

            actual shouldBe Res.Success(productPage)
        }

    @ParameterizedTest
    @MethodSource("errors")
    fun `Should correctly map errors when getProducts is called given error response`(
        error: NetworkError,
    ) = runTest {
        givenFailedProductPageRequest(error)

        val actual = sut.getProducts()

        actual shouldBe Res.Error(error)
    }

    private fun givenSuccessfulProductPageRequest() {
        coEvery { productRemoteDataSource.getProductPage() } returns Res.Success(productPageResponse)
    }

    private fun givenFailedProductPageRequest(error: NetworkError = NetworkError.ConnectionError) {
        coEvery { productRemoteDataSource.getProductPage() } returns Res.Error(error)
    }

    companion object {
        @JvmStatic
        fun errors() = listOf(
            named("Unauthorized", NetworkError.HttpError(code = 401)),
            named("Forbidden", NetworkError.HttpError(code = 403)),
            named("Server Error", NetworkError.HttpError(code = 500)),
            named("Unexpected Error", NetworkError.UnknownError),
            named("No Body Error", NetworkError.NoBodyError),
            named("Connection Error", NetworkError.ConnectionError),
        )
    }
}
