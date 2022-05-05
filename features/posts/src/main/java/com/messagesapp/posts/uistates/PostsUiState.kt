package com.messagesapp.posts.uistates

import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.UserPost

sealed class PostsUiState{
    object Loading : PostsUiState()
    data class PostsList(val data: List<Posts>) : PostsUiState()
    data class PostDetail(val data: UserPost) : PostsUiState()
    data class PostComments(val data: List<Comments>) : PostsUiState()
    object Error : PostsUiState()
    object ErrorConnection : PostsUiState()
}