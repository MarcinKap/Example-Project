package com.example.data.product.usecase

import com.example.core.networking.model.Res
import com.example.data.product.motherdata.ProductPageMotherData.productPage
import com.example.data.product.repository.ProductRepository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ProductPageUseCaseTest {

    lateinit var sut: ProductPageUseCase
    private val productRepository: ProductRepository = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        sut = ProductPageUseCase(productRepository)
    }

    @Test
    fun `Should call repository when invoke is called`() = runTest {
        sut.invoke()

        coVerify { sut.invoke() }
    }

    @Test
    fun `Should return product page invoke is called`() = runTest {
        val productPage = productPage

        coEvery { productRepository.getProducts() } returns Res.Success(productPage)

        val actual = sut.invoke()

        actual shouldBe Res.Success(productPage)
    }
}
