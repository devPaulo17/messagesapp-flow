package com.messagesapp.data.local

import com.messagesapp.data.local.Daos.PostDao
import com.messagesapp.data.remote.posts.toCommentsList
import com.messagesapp.data.remote.posts.toPostsList
import com.messagesapp.data.remote.posts.toUserPostsData
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.UserPost
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

    override suspend fun getCommentsByPostId(comments: Int):Flow<List<Comments>> {
       return postDao.getCommentsByPostId(comments).map {
           it.toCommentsList()
       }
    }

    override suspend fun getAllPosts(): Flow<List<Posts>> = postDao.getAllPosts().map {
        it.toPostsList()
    }

    override suspend fun getPostDetail(postId: Int): Flow<UserPost> {
        return postDao.getPostDetailById(postId).map {
            it.toUserPostsData()
        }
    }

    override suspend fun deleteAllPosts() {
        postDao.deleteAllPosts()
    }

    override suspend fun deletePostById(postId: Int) {
        postDao.deletePostById(postId)
    }

    override suspend fun addPostToFavorites(postId: Int) {
        postDao.addPostToFavorites(postId)
    }

    override suspend fun remotePostFromFavorites(postId: Int) {
        postDao.removePostFromFavorites(postId)
    }
}