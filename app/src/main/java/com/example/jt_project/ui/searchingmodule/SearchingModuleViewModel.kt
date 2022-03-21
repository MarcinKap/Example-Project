package com.example.jt_project.ui.searchingmodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jt_project.api.models.Data
import com.example.jt_project.api.models.PostList
import com.example.jt_project.api.repositories.PostListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchingModuleViewModel @Inject constructor(
    private val postListRepository: PostListRepository
) : ViewModel() {


    private var _postList = MutableStateFlow<List<Data>>(emptyList())
    val postList: Flow<List<Data>> = _postList


    init {
        viewModelScope.launch {
            _postList.tryEmit(postListRepository.getPostList().data)
        }
    }


}