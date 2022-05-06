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

const val GET_POSTS_AND_USERS = "postAndUsers"
const val GET_COMMENTS = "comments"

class PostsRepositoryImpl(
    private val remoteDataSource: RemotePostsDataSource,
    private val localDataSource: LocalPostsDataSource
) : PostsRepository {

    private var isFromDeleteAction: Boolean = false

    override suspend fun getAllPosts(forceUpdate: Boolean): Flow<HandleResult<List<Posts>>> = flow {

        if (forceUpdate) {
            getRemoteData(GET_POSTS_AND_USERS)
        } else {
            localDataSource.getAllPosts().collect {
                if (it.isNotEmpty()) {
                    emit(HandleResult.Success(it))
                } else if (!isFromDeleteAction) {
                    getRemoteData(GET_POSTS_AND_USERS)
                } else {
                    emit(HandleResult.Error(""))
                }
            }
        }

    }.flowOn(Dispatchers.IO)

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
                getRemoteData(GET_COMMENTS, postId)
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteAllPosts(forceUpdate: Boolean) {
        withContext(Dispatchers.IO) {
            isFromDeleteAction = forceUpdate
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

    private suspend fun getRemoteData(action: String, postId: Int = 0) {
        withContext(Dispatchers.IO) {

            when (action) {
                GET_POSTS_AND_USERS -> {
                    launch {
                        getPosts()
                    }
                    launch {
                        getUsers()
                    }
                }
                GET_COMMENTS -> {
                    launch {
                        getCommentsByPostId(postId)
                    }
                }
                else -> {}
            }
        }
    }

    private suspend fun getUsers() {
        when (val result = remoteDataSource.getAllUsers()) {
            is HandleResult.Success -> {
                saveUsersData(result.data)
            }
            else -> {}
        }
    }

    private suspend fun getPosts() {
        when (val result = remoteDataSource.getAllPosts()) {
            is HandleResult.Success -> {
                savePostsData(result.data)
            }
            else -> {}
        }
    }

    private suspend fun getCommentsByPostId(postId: Int) {
        when (val result = remoteDataSource.getCommentsByPostId(postId)) {
            is HandleResult.Success -> {
                saveCommentsData(result.data)
            }
            else -> {}
        }
    }

    private suspend fun saveUsersData(users: List<Users>) {
        localDataSource.saveAllUsers(users)
    }

    private suspend fun savePostsData(data: List<Posts>) {
        localDataSource.saveAllPost(data)
    }

    private suspend fun saveCommentsData(data: List<Comments>) {
        localDataSource.saveCommentsByPostId(data)
    }
}
