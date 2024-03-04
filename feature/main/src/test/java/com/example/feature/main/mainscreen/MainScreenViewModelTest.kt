package com.example.feature.main.mainscreen

import com.example.core.networking.model.NetworkError
import com.example.core.networking.model.Res
import com.example.data.product.model.internal.ProductPage
import com.example.data.product.usecase.CategoriesUseCase
import com.example.data.product.usecase.ProductPageByCategoryUseCase
import com.example.data.product.usecase.ProductPageUseCase
import com.example.feature.main.mainscreen.ProductMotherData.product_1
import com.example.feature.main.mainscreen.ProductMotherData.product_2
import com.example.feature.main.mainscreen.composable.previewData.previewProductList
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MainScreenViewModelTest {
    private val productPageUseCase: ProductPageUseCase = mockk(relaxed = true)
    private val productPageByCategoryUseCase: ProductPageByCategoryUseCase = mockk(relaxed = true)
    private val categoriesUseCase: CategoriesUseCase = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Test
    fun `Should invoke ProductPageUseCase when screen launches`() = runTest {
        givenASuccessfulResultOnProductPageRequest()
        givenASuccessfulResultOnCategoryListRequest()

        initializeViewModel()

        coVerify { productPageUseCase.invoke() }
    }

    @Test
    fun `Should invoke CategoriesUseCase when screen launches`() = runTest {
        givenASuccessfulResultOnProductPageRequest()
        givenASuccessfulResultOnCategoryListRequest()

        initializeViewModel()

        coVerify { productPageUseCase.invoke() }
    }

    @Test
    fun `Should download product list when screen launches`() = runTest {
        givenASuccessfulResultOnProductPageRequest(anyProductPage)
        givenASuccessfulResultOnCategoryListRequest(anyCategoriesReponse)

        val sut = initializeViewModel()

        sut.uiState.value.should {
            it.products shouldBe previewProductList
            it.categories shouldBe anyCategoriesList
            it.state shouldBe MainState.Success
        }
    }

    @ParameterizedTest
    @MethodSource("errors")
    fun `Should update state to error when invoke productPage with error response`(
        networkError: NetworkError,
    ) = runTest {
        givenAnErrorOnProductPageRequest(networkError)

        val sut = initializeViewModel()

        sut.uiState.value.should {
            it.state shouldBe MainState.Error(networkError)
        }
    }

    private fun givenASuccessfulResultOnProductPageRequest(
        productPage: ProductPage = anyProductPage,
    ) {
        coEvery { productPageUseCase.invoke() } returns Res.Success(productPage)
    }

    private fun givenASuccessfulResultOnCategoryListRequest(
        categories: List<String> = emptyList(),
    ) {
        coEvery { categoriesUseCase.invoke() } returns Res.Success(categories)
    }

    private fun givenAnErrorOnProductPageRequest(error: NetworkError) {
        coEvery {
            productPageUseCase.invoke()
        } returns Res.Error(error)
    }

    private fun initializeViewModel() = MainViewModel(
        productPageUseCase = productPageUseCase,
        productPageByCategoryUseCase = productPageByCategoryUseCase,
        categoriesUseCase = categoriesUseCase,
        ioDispatcher = testDispatcher,
    )

    private companion object {
        val anyProductPage = ProductPage(
            products = listOf(
                product_1,
                product_2,
            ),
            total = 0,
            skip = 0,
            limit = 0,
        )

        val anyCategoriesReponse = listOf("car", "bikes", "fruits")
        val anyCategoriesList = listOf("All", "car", "bikes", "fruits")

        @JvmStatic
        fun errors() = listOf(
            NetworkError.ConnectionError,
            NetworkError.NoBodyError,
            NetworkError.UnknownError,
        )
    }
}
