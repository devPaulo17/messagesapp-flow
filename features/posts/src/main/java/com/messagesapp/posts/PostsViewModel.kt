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

    fun getAllPosts(forceUpdate: Boolean = false) {
        viewModelScope.launch {
            postsRepository.getAllPosts(forceUpdate).collectLatest {
                when (it) {
                    is HandleResult.Success -> _viewState.value = PostsUiState.PostsList(it.data)
                    is HandleResult.Error-> _viewState.value = PostsUiState.Error
                }
            }
        }
    }

    fun setPostId(postId: Int) {
        _viewState.value = PostsUiState.PostId(postId)
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


    fun deleteAllPosts(needUpdate: Boolean) {
        viewModelScope.launch {
            postsRepository.deleteAllPosts(needUpdate)
        }
    }

    fun deletePostById(postId: Int) {
        viewModelScope.launch {
            postsRepository.deletePostById(postId)
        }
    }

    fun addPostToFavorites(postId: Int) {
        viewModelScope.launch {
            postsRepository.addPostToFavorites(postId)
        }
    }

    fun deletePostFromFavorites(postId: Int) {
        viewModelScope.launch {
            postsRepository.remotePostFromFavorites(postId)
        }
    }
}