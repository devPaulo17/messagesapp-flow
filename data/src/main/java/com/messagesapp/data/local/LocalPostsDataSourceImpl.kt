package com.messagesapp.data.local

import com.messagesapp.data.local.Daos.PostDao
import com.messagesapp.data.remote.entities.CommentsApi
import com.messagesapp.data.remote.entities.PostsApi
import com.messagesapp.data.remote.entities.UsersApi
import com.messagesapp.data.remote.posts.toListResults
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
        it.toListResults()
    }

    fun List<com.messagesapp.data.local.entities.Posts>.toListResults2(): List<Posts> {
        return map { item ->
            Posts(
                userId = item.userId,
                id = item.id,
                title = item.title,
                body = item.body
            )
        }
    }


}

fun List<Comments>.toCommentsList(): List<com.messagesapp.data.local.entities.Comments> {
    return map { item ->
        com.messagesapp.data.local.entities.Comments(
            postId = item.postId,
            id = item.id,
            name = item.name,
            email = item.email,
            body = item.body
        )
    }
}

fun List<Users>.toUsersList(): List<com.messagesapp.data.local.entities.Users> {
    return map { item ->
        com.messagesapp.data.local.entities.Users(
            id = item.id,
            name = item.name,
            userName = item.userName,
            email = item.email,
            webSite = item.webSite
        )
    }
}


fun List<Posts>.toListResults(): List<PostsApi> {
    return map { item ->
        PostsApi(
            userId = item.userId,
            id = item.id,
            title = item.title,
            body = item.body
        )
    }
}