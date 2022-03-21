package com.example.jt_project.api.repositories

import com.example.jt_project.api.RetrofitApi
import com.example.jt_project.api.models.PostList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostListRepositoryImpl  @Inject constructor(
    private val retrofitApi: RetrofitApi,
) : PostListRepository {

    override fun getPostList(): Flow<PostList> {
        return retrofitApi.getPostList()
    }

}