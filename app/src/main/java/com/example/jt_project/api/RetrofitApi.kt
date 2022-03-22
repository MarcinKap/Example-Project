package com.example.jt_project.api

import androidx.annotation.Keep
import com.example.jt_project.api.models.Post
import com.example.jt_project.api.models.PostList
import retrofit2.http.GET
import retrofit2.http.Path

@Keep
interface RetrofitApi {

    @GET("post?limit=10")
    suspend fun getPostList(): PostList

    @GET("post/{id}")
    suspend fun getPostByPostId(@Path("id") id: String): Post
}