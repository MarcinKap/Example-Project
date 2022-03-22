package com.example.jt_project

import androidx.lifecycle.SavedStateHandle
import com.example.jt_project.api.Res
import com.example.jt_project.api.Status
import com.example.jt_project.api.models.Owner
import com.example.jt_project.api.models.Post
import com.example.jt_project.api.models.PostList
import com.example.jt_project.api.repositories.PostListRepositoryImpl
import com.example.jt_project.ui.searchingmodule.SearchingModuleViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@ExperimentalCoroutinesApi
@RunWith(BlockJUnit4ClassRunner::class)
class SearchingModuleViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getPostList_Success() = testScope.runTest {
        val postList =
            PostList(
                posts = arrayListOf(
                    Post(
                        id = "id",
                        image = "url",
                        likes = 34,
                        tags = arrayListOf("dog", "holiday"),
                        text = "Text",
                        publishDate = "2020-05-24T07:44:17.738Z",
                        owner = Owner(
                            id = "0",
                            title = "title",
                            firstName = "Gary",
                            lastName = "Owen",
                            picture = "url"
                        )
                    )
                ),
                total = 1,
                page = 1,
                limit = 1
            )


        val repo = mockk<PostListRepositoryImpl>()
        coEvery { repo.getPostList() } returns Res.success(postList)

        val viewModel = SearchingModuleViewModel(repo)

        val list = mutableListOf<Res<PostList>>()
        viewModel.postList.take(2).toList(list)

        Assert.assertTrue(list[0].status == Status.LOADING)
        Assert.assertTrue(list[1].status == Status.SUCCESS)
        Assert.assertTrue(postList == list[1].data)
    }


}