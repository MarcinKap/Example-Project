package com.example.jt_project.api.repositories

import com.example.jt_project.api.RetrofitApi
import com.example.jt_project.api.models.PostList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class PostListRepositoryImpl @Inject constructor(
    private val retrofitApi: RetrofitApi,
) : PostListRepository {

    override suspend fun getPostList(): PostList {

       return retrofitApi.getPostList()
    }

}