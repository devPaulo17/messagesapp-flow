package com.messagesapp.data.local

import com.messagesapp.data.local.Daos.PostDao
import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
import com.messagesapp.data.entities.UsersApi
import com.messagesapp.data.remote.posts.toPostsList
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.Users
import com.messagesapp.domain.repositories.posts.LocalPostsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPostsDataSourceImpl(private val postDao: PostDao) : LocalPostsDataSource {
    override suspend fun saveAllPost(posts: List<Posts>) {
        postDao.savePosts(posts.toListResults())
    }

    override suspend fun saveAllUsers(users: List<Users>) {
        postDao.saveUsers(users.toUsersList())
    }

    override suspend fun saveCommentsByPostId(comments: List<Comments>) {
        postDao.saveCommentsByPostId(comments.toCommentsList())
    }

    override suspend fun getAllPosts(): Flow<List<Posts>> = postDao.getAllDogs().map {
        it.toPostsList()
    }
}