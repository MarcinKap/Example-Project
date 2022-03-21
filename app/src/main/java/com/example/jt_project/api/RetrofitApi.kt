package com.example.jt_project.api

import androidx.annotation.Keep
import com.example.jt_project.api.models.PostList
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
interface RetrofitApi {

    @GET("post?limit=10")
    suspend fun getPostList(): PostList


}