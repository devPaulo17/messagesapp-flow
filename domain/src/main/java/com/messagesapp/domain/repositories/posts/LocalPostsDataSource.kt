package com.messagesapp.domain.repositories.posts

import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.UserPost
import com.messagesapp.domain.entities.posts.Users
import kotlinx.coroutines.flow.Flow

interface LocalPostsDataSource {
    suspend fun saveAllPost(posts: List<Posts>)
    suspend fun saveAllUsers(users: List<Users>)
    suspend fun saveCommentsByPostId(posts: List<Comments>)
    suspend fun getCommentsByPostId(posts: Int): Flow<List<Comments>>
    suspend fun getAllPosts(): Flow<List<Posts>>
    suspend fun getPostDetail(postId: Int): Flow<UserPost>
    suspend fun deleteAllPosts()
    suspend fun deletePostById(postId: Int)
    suspend fun addPostToFavorites(postId: Int)
    suspend fun remotePostFromFavorites(postId: Int)
}