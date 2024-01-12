package com.example.data.product.datasource.remote

import com.example.core.networking.model.Res
import com.example.core.testing.endpoints.GET
import com.example.core.testing.endpoints.MockServerTest
import com.example.core.testing.endpoints.enqueueJsonResponse
import com.example.core.testing.endpoints.methodShouldBe
import com.example.core.testing.endpoints.pathShouldBe
import com.example.data.product.datasource.remote.product.ProductRemoteDataSourceImpl
import com.example.data.product.motherdata.ProductPageResponseMotherData.productPageResponse
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductRemoteDataSourceImplTest : MockServerTest() {

    private lateinit var sut: ProductRemoteDataSourceImpl

    @BeforeEach
    fun setUp() {
        sut = ProductRemoteDataSourceImpl(buildApi())
    }

    @Test
    fun `Should call request product page correctly`() = runTest {
        givenSuccessDownloadProductPage()

        sut.getProductPage()

        server.takeRequest().should { request ->
            request methodShouldBe GET
            request pathShouldBe "/products"
        }
    }

    @Test
    fun `Should serialize response correctly when calling product page endpoint`() = runTest {
        givenSuccessDownloadProductPage()

        val actual = sut.getProductPage()

        val result = (actual as Res.Success).result

        result shouldBe productPageResponse
    }

    private fun givenSuccessDownloadProductPage() {
        server.enqueueJsonResponse("responses/product/200_ok_product_response.json")
    }
}
