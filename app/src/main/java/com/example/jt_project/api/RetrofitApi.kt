package com.example.jt_project.api

import com.example.jt_project.api.models.PostList
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("post?limit=10")
    fun getPostList(): Flow<PostList>




}