package com.messagesapp.data.remote.retrofit.ApiServices

import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
import com.messagesapp.data.entities.UsersApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiService {

    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostsApi>>

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: Int): Response<List<CommentsApi>>

    @GET("users")
    suspend fun getUsers(): Response<List<UsersApi>>
}