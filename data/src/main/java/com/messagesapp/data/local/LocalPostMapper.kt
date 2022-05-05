package com.messagesapp.data.local

import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
import com.messagesapp.data.entities.UsersApi
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.Users

fun List<Comments>.toCommentsList(): List<CommentsApi> {
    return map { item ->
        CommentsApi(
            postId = item.postId,
            id = item.id,
            name = item.name,
            email = item.email,
            body = item.body
        )
    }
}

fun List<Users>.toUsersList(): List<UsersApi> {
    return map { item ->
        UsersApi(
            id = item.id,
            name = item.name,
            userName = item.userName,
            email = item.email,
            webSite = item.webSite
        )
    }
}


fun List<Posts>.toListResults(): List<PostsApi> {
    return map { item ->
        PostsApi(
            userId = item.userId,
            id = item.id,
            title = item.title,
            body = item.body
        )
    }
}
