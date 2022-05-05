package com.messagesapp.posts


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.repositories.posts.PostsRepository
import com.messagesapp.posts.uistates.PostsUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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


    fun getComments(postId: Int) {
        viewModelScope.launch {
            postsRepository.getComments(postId).collectLatest {
                when (it) {
                    is HandleResult.Success -> {
                        _viewState.value = PostsUiState.PostComments(it.data)
                    }
                }
            }
        }
    }

    fun getPostDetail(postId: Int) {
        viewModelScope.launch {
            postsRepository.getPostDetail(postId).collectLatest {
                when (it) {
                    is HandleResult.Success -> {
                        _viewState.value = PostsUiState.PostDetail(it.data)
                    }
                }
            }
        }
    }


    fun deleteAllPosts() {
        viewModelScope.launch {
            postsRepository.deleteAllPosts()


        }
    }

    fun deletePostById() {
        viewModelScope.launch {
            postsRepository.deletePostById(1)
        }
    }

    fun addPostToFavorites() {
        viewModelScope.launch {
            postsRepository.addPostToFavorites(3)
        }
    }

    fun remotePostFromFavorites() {
        viewModelScope.launch {
            postsRepository.remotePostFromFavorites(2)
        }
    }
}