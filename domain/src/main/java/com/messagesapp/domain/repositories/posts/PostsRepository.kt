package com.messagesapp.domain.repositories.posts

import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.UserPost
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun getAllPosts(): Flow<HandleResult<List<Posts>>>
    suspend fun getPostDetail(postId: Int): Flow<HandleResult<UserPost>>
    suspend fun getComments(postId: Int): Flow<HandleResult<List<Comments>>>
    suspend fun deleteAllPosts()
    suspend fun deletePostById(postId: Int)
    suspend fun addPostToFavorites(postId: Int)
    suspend fun remotePostFromFavorites(postId: Int)

}