package com.messagesapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
import com.messagesapp.data.local.AppDatabase
import com.messagesapp.data.local.Daos.PostDao
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    private lateinit var postDao: PostDao
    private lateinit var db: AppDatabase

    private val posts: List<PostsApi> =
        listOf(PostsApi(1, 1, "Some Random Email", "test@gmail.com", false))

    private val comments: List<CommentsApi> =
        listOf(CommentsApi(1, 1, "Some Random Email", "test@gmail.com", "asdasds"))


    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        postDao = db.postsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertPosts() = runBlocking {
        postDao.savePosts(posts)
        val posts = postDao.getAllPosts().first()
        assertEquals(posts[0].title, posts[0].title)
    }

    @Test
    @Throws(Exception::class)
    fun insertComments() = runBlocking {
        postDao.saveCommentsByPostId(comments)
        val comments = postDao.getCommentsByPostId(1).first()
        assertEquals(comments[0].email, comments[0].email)
    }

    @Test
    @Throws(Exception::class)
    fun addPostToFavorites() = runBlocking {
        postDao.savePosts(posts)
        postDao.addPostToFavorites(1)
        val comments = postDao.getAllPosts().first()
        assertEquals(true, comments[0].isFavorite)
    }

    @Test
    @Throws(Exception::class)
    fun removedPostToFavorites() = runBlocking {
        postDao.savePosts(posts)
        postDao.removePostFromFavorites(1)
        val comments = postDao.getAllPosts().first()
        assertEquals(false, comments[0].isFavorite)
    }

    @Test
    @Throws(Exception::class)
    fun deletePostById() = runBlocking {
        postDao.savePosts(posts)
        postDao.deletePostById(1)
        val comments = postDao.getAllPosts().first()
        assertEquals(0, comments.size)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllPost() = runBlocking {
        postDao.savePosts(posts)
        postDao.deleteAllPosts()
        val comments = postDao.getAllPosts().first()
        assertEquals(0, comments.size)
    }
}