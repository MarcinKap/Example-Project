package com.example.jt_project.ui.postdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jt_project.api.Res
import com.example.jt_project.models.Post
import com.example.jt_project.api.repositories.PostListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val postListRepository: PostListRepository
) : ViewModel() {

    private val entryId = savedStateHandle.get<String>("id")!!

    private var _post = MutableStateFlow<Res<Post>>(Res.loading())
    val post: StateFlow<Res<Post>> = _post

    init {
        getPost()
    }

    fun getPost() {
        viewModelScope.launch {
            _post.tryEmit(postListRepository.getPostByPostId(entryId))
        }
    }
}