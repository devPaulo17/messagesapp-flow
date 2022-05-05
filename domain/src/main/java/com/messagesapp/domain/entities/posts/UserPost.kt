package com.messagesapp.domain.entities.posts

data class UserPost(
    val userName: String?,
    val userEmail: String,
    val userNickName: String,
    val userWebSite: String,
    val postBoddy: String?
)
