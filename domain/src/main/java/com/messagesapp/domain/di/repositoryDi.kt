package com.messagesapp.domain.di

import com.messagesapp.domain.repositories.posts.PostsRepository
import com.messagesapp.domain.repositories.posts.PostsRepositoryImpl
import org.koin.dsl.module

val repositoryDi = module {

    factory<PostsRepository> {
        PostsRepositoryImpl(get(),get())
    }
}