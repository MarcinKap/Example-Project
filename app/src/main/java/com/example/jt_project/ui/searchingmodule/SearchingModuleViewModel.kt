package com.example.jt_project.ui.searchingmodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jt_project.api.Res
import com.example.jt_project.models.PostList
import com.example.jt_project.api.repositories.PostListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchingModuleViewModel @Inject constructor(
    private val postListRepository: PostListRepository
) : ViewModel() {


    private var _postList = MutableStateFlow<Res<PostList>>(Res.loading())
    val postList: StateFlow<Res<PostList>> = _postList


    init {
        viewModelScope.launch {
            _postList.tryEmit(postListRepository.getPostList())
        }
    }


}