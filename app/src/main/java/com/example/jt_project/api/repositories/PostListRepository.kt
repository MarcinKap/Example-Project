package com.example.jt_project.api.repositories

import com.example.jt_project.api.Res
import com.example.jt_project.api.models.Data
import com.example.jt_project.api.models.PostList
import kotlinx.coroutines.flow.Flow

interface PostListRepository  {

    suspend fun getPostList(): PostList
    suspend fun getPostByPostId(id: String): Res<Data>


}