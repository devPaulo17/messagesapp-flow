package com.messagesapp.data

import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
import com.messagesapp.data.entities.UsersApi
import com.messagesapp.data.remote.posts.toCommentsList
import com.messagesapp.data.remote.posts.toPostsList
import com.messagesapp.data.remote.posts.toUsersList
import org.junit.Test
import kotlin.test.assertEquals

class PostMapperTest {

    private val postResults = listOf(
        PostsApi(
            1,
            1,
            "asdasd",
            "asdasd"
        )
    )

    private val comments: List<CommentsApi> =
        listOf(CommentsApi(1, 1, "Some Random Email", "test@gmail.com", "asdasds"))


    private val users: List<UsersApi> =
        listOf(UsersApi(1, "", "Some Random Email", "test@gmail.com", "asdasds"))


    @Test
    fun `transform to result posts()`() {
        postResults.toPostsList()
            .forEachIndexed { index, result ->
                with(postResults[index]) {
                    assertEquals(id, result.id)
                    assertEquals(userId, result.userId)
                    assertEquals(title, result.title)
                    assertEquals(body, result.body)
                }
            }
    }

    @Test
    fun `transform to result comments()`() {
        comments.toCommentsList()
            .forEachIndexed { index, result ->
                with(comments[index]) {
                    assertEquals(postId, result.postId)
                    assertEquals(id, result.id)
                    assertEquals(name, result.name)
                    assertEquals(email, result.email)
                    assertEquals(body, result.body)
                }
            }
    }

    @Test
    fun `transform to result users()`() {
        users.toUsersList()
            .forEachIndexed { index, result ->
                with(users[index]) {
                    assertEquals(id, result.id)
                    assertEquals(name, result.name)
                    assertEquals(userName, result.userName)
                    assertEquals(email, result.email)
                    assertEquals(webSite, result.webSite)
                }
            }
    }
}