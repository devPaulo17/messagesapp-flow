package com.messagesapp.domain.repositories.posts

import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Posts
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun getAllPosts(): Flow<HandleResult<List<Posts>>>
    suspend fun getPostDetail(): Flow<String>

}