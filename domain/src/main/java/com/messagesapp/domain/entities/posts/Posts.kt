package com.messagesapp.domain.entities.posts


data class Posts(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
