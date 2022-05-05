package com.messagesapp.posts.uistates

import com.messagesapp.domain.entities.posts.Posts

sealed class PostsUiState{
    object Loading : PostsUiState()
    data class PostsList(val data: List<Posts>) : PostsUiState()
    object Error : PostsUiState()
    object ErrorConnection : PostsUiState()
}