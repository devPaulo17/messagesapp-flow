package com.messagesapp.data.remote.entities

import kotlinx.serialization.Serializable

@Serializable
data class CommentsApi(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body:String
)