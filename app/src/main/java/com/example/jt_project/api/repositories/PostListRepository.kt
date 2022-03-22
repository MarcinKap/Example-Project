package com.example.jt_project.api.repositories

import com.example.jt_project.api.Res
import com.example.jt_project.api.models.Post
import com.example.jt_project.api.models.PostList

interface PostListRepository  {

    suspend fun getPostList(): Res<PostList>
    suspend fun getPostByPostId(id: String): Res<Post>


}