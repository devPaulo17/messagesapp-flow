package com.messagesapp.domain.repositories.posts

import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.Users


interface RemotePostsDataSource {
    suspend fun getAllPosts(): HandleResult<List<Posts>>
    suspend fun getCommentsByPostId(postId: Int): HandleResult<List<Comments>>
    suspend fun getAllUsers():HandleResult<List<Users>>
}