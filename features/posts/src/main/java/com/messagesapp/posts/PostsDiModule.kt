package com.messagesapp.posts

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postsModule = module {
    viewModel { PostsViewModel(get()) }
}