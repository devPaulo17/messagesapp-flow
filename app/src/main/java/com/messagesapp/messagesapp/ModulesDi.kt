package com.messagesapp.messagesapp

import com.messagesapp.data.remote.networkModule
import com.messagesapp.domain.di.repositoryDi
import com.messagesapp.posts.postsModule

val MODULES_DI = listOf(
    networkModule,
    postsModule,
    repositoryDi
)