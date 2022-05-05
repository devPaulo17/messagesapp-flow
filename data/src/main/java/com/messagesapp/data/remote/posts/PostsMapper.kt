package com.messagesapp.data.remote.posts


import com.messagesapp.data.remote.entities.CommentsApi
import com.messagesapp.data.remote.entities.PostsApi
import com.messagesapp.data.remote.entities.UsersApi
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.Users

fun List<PostsApi>.toListResults(): List<Posts> {
    return map { item ->
        Posts(
            userId = item.userId,
            id = item.id,
            title = item.title,
            body = item.body
        )
    }
}

fun List<CommentsApi>.toCommentsList(): List<Comments> {
    return map { item ->
        Comments(
            postId = item.postId,
            id = item.id,
            name = item.name,
            email = item.email,
            body = item.body
        )
    }
}

fun List<UsersApi>.toUsersList(): List<Users> {
    return map { item ->
        Users(
            id = item.id,
            name = item.name,
            userName = item.userName,
            email = item.email,
            webSite = item.webSite
        )
    }
}
