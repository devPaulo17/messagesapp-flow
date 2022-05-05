package com.messagesapp.domain.repositories.posts

import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.UserPost
import com.messagesapp.domain.entities.posts.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsRepositoryImpl(
    private val remoteDataSource: RemotePostsDataSource,
    private val localDataSource: LocalPostsDataSource
) : PostsRepository {


    override suspend fun getAllPosts(): Flow<HandleResult<List<Posts>>> = flow {

        localDataSource.getAllPosts().collect {
            if (it.isNotEmpty()) {
                emit(HandleResult.Success(it))
            } else {
                hola()
            }

        }
    }.flowOn(Dispatchers.IO)


    private suspend fun hola() {
        withContext(Dispatchers.IO) {
            launch {
                val result = remoteDataSource.getAllUsers()
                saveUsersData(validateResponse(result))
            }
            launch {
                val result = remoteDataSource.getAllPosts()
                savePostsData(validateResponse(result))
            }
        }
    }

    override suspend fun getPostDetail(postId: Int): Flow<HandleResult<UserPost>> = flow {
        localDataSource.getPostDetail(postId).collect {
            emit(HandleResult.Success(it))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getComments(postId: Int): Flow<HandleResult<List<Comments>>> = flow {
        localDataSource.getCommentsByPostId(postId).collect {
            if (it.isNotEmpty()) {
                emit(HandleResult.Success(it))
            } else {
                hola1(postId)
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun hola1(postId: Int) {
        withContext(Dispatchers.IO) {
            val result = remoteDataSource.getCommentsByPostId(postId)
            localDataSource.saveCommentsByPostId(validateResponse(result) as List<Comments>)
        }
    }


    override suspend fun deleteAllPosts() {
        withContext(Dispatchers.IO) {
            localDataSource.deleteAllPosts()
        }
    }

    override suspend fun deletePostById(postId: Int) {
        withContext(Dispatchers.IO) {
            localDataSource.deletePostById(postId)
        }
    }

    override suspend fun addPostToFavorites(postId: Int) {
        withContext(Dispatchers.IO) {
            localDataSource.addPostToFavorites(postId)
        }
    }

    override suspend fun remotePostFromFavorites(postId: Int) {
        withContext(Dispatchers.IO) {
            localDataSource.remotePostFromFavorites(postId)
        }
    }

    private suspend fun saveUsersData(data: List<Any>) {
        localDataSource.saveAllUsers(data as List<Users>)
    }

    private suspend fun savePostsData(data: List<Any>) {
        localDataSource.saveAllPost(data as List<Posts>)
    }

    private suspend fun saveCommentsData(data: List<Any>) {
        localDataSource.saveCommentsByPostId(data as List<Comments>)
    }

    private fun validateResponse(result: HandleResult<List<Any>>): List<Any> {

        return when (result) {
            is HandleResult.Success -> {
                result.data
            }
            else -> {
                listOf()
            }
        }

    }

}
