package com.messagesapp.domain.repositories.posts

import androidx.lifecycle.LiveData
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.Users
import kotlinx.coroutines.flow.Flow


interface LocalPostsDataSource {
    suspend fun saveAllPost(posts: List<Posts>)
    suspend fun saveAllUsers(posts: List<Users>)
    suspend fun saveCommentsByPostId(posts: List<Comments>)
    suspend fun getAllPosts(): Flow<List<Posts>>
}