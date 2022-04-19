package com.example.jt_project

import com.example.jt_project.api.RetrofitApi
import com.example.jt_project.api.Status
import com.example.jt_project.models.Post
import com.example.jt_project.models.Owner
import com.example.jt_project.api.repositories.PostListRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner


@RunWith(BlockJUnit4ClassRunner::class)
class PostListRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPost_success() = testScope.runTest {
        val data = Post(
            id = "id",
            image = "url",
            likes = 34,
            tags = arrayListOf("dog", "holiday"),
            text = "Text",
            publishDate = "",
            owner = Owner(
                id = "0",
                title = "title",
                firstName = "Gary",
                lastName = "Owen",
                picture = "url"
            )
        )

        val retrofitApi = mockk<RetrofitApi>()
        coEvery { retrofitApi.getPostByPostId(any()) } returns data

        val repo = PostListRepositoryImpl(retrofitApi)
        val response = repo.getPostByPostId("id")
        Assert.assertTrue(response.status == Status.SUCCESS)
        Assert.assertTrue(response.data?.id == "id")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPost_failed() = testScope.runTest {

        val retrofitApi = mockk<RetrofitApi>()
        coEvery { retrofitApi.getPostByPostId(any()) } throws RuntimeException()

        val repo = PostListRepositoryImpl(retrofitApi)
        val response = repo.getPostByPostId("id")
        Assert.assertTrue(response.status == Status.ERROR)
    }
}