package com.example.jt_project.api.repositories

import com.example.jt_project.api.Res
import com.example.jt_project.api.RetrofitApi
import com.example.jt_project.api.models.Post
import com.example.jt_project.api.models.PostList
import java.lang.Exception
import javax.inject.Inject

class PostListRepositoryImpl @Inject constructor(
    private val retrofitApi: RetrofitApi,
) : PostListRepository {

    override suspend fun getPostList(): Res<PostList> {
        try {
            return Res.success(retrofitApi.getPostList())
        }catch (ex: Exception){
            return Res.error(ex)
        }
    }

    override suspend fun getPostByPostId(id: String): Res<Post> {
        try {
            return Res.success(retrofitApi.getPostByPostId(id = id))
        }catch (ex: Exception){
            return Res.error(ex)
        }
    }


}