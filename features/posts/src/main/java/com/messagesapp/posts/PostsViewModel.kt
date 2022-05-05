package com.messagesapp.posts


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.repositories.posts.PostsRepository
import com.messagesapp.posts.uistates.PostsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class PostsViewModel(private val postsRepository: PostsRepository) : ViewModel() {

    private val _viewState = MutableLiveData<PostsUiState>()
    val viewState: LiveData<PostsUiState> = _viewState

    fun getAllPosts() {
        viewModelScope.launch {
            postsRepository.getAllPosts().collectLatest {
                when (it) {
                    is HandleResult.Success -> {
                        _viewState.value = PostsUiState.PostsList(it.data)
                    }
                }
            }
        }
    }


    fun getPostDetail() {
        viewModelScope.launch {
            postsRepository.getPostDetail()
        }
    }
}