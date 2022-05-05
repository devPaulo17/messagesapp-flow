package com.messagesapp.domain.repositories.posts

import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
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

    override suspend fun getPostDetail(postId: Int):Flow<String> = flow<String> {
            val hola = remoteDataSource.getPostDetail(postId)
        val aa =1
            emit("asdasd")

    }.flowOn(Dispatchers.IO)

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
