package com.example.jt_project.api.repositories

import com.example.jt_project.api.models.PostList
import kotlinx.coroutines.flow.Flow

interface PostListRepository  {

    fun getPostList(): Flow<PostList>


}