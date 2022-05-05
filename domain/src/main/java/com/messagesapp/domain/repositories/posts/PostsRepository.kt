package com.messagesapp.domain.repositories.posts

import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Posts
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun getAllPosts(): Flow<HandleResult<List<Posts>>>
    suspend fun getPostDetail(postId: Int): Flow<String>
    suspend fun deleteAllPosts()
    suspend fun deletePostById(postId: Int)
    suspend fun addPostToFavorites(postId: Int)
    suspend fun remotePostFromFavorites(postId: Int)

}