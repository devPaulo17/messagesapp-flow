package com.messagesapp.data.remote.posts

import com.messagesapp.data.remote.retrofit.ApiServices.PostsApiService
import com.messagesapp.data.remote.retrofit.executeRetrofitRequest
import com.messagesapp.data.remote.retrofit.handleResultRetrofit
import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.Users
import com.messagesapp.domain.repositories.posts.RemotePostsDataSource

class RemotePostsDataSourceImpl( private val postsApiService: PostsApiService) : RemotePostsDataSource{
    override suspend fun getAllPosts(): HandleResult<List<Posts>> {
        val result = executeRetrofitRequest {
            postsApiService.getAllPosts()
        }

        return handleResultRetrofit(result) { results ->
            results.toListResults()
        }
    }

    override suspend fun getPostDetail():HandleResult<List<Comments>> {
        val result = executeRetrofitRequest {
            postsApiService.getComments()
        }

        return handleResultRetrofit(result) { results ->
            results.toCommentsList()
        }
    }

    override suspend fun getAllUsers(): HandleResult<List<Users>> {
        val result = executeRetrofitRequest {
            postsApiService.getUsers()
        }

        return handleResultRetrofit(result) { results ->
            results.toUsersList()
        }
    }
}