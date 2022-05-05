package com.messagesapp.data.remote.retrofit.ApiServices

import com.messagesapp.data.remote.entities.CommentsApi
import com.messagesapp.data.remote.entities.PostsApi
import com.messagesapp.data.remote.entities.UsersApi
import retrofit2.Response
import retrofit2.http.GET

interface PostsApiService {

    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostsApi>>

    @GET("posts/1/comments")
    suspend fun getComments(): Response<List<CommentsApi>>

    @GET("users")
    suspend fun getUsers(): Response<List<UsersApi>>
}