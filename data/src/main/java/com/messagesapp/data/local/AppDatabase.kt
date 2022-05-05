package com.messagesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.messagesapp.data.local.Daos.PostDao
import com.messagesapp.data.local.entities.Comments
import com.messagesapp.data.local.entities.Posts
import com.messagesapp.data.local.entities.Users
import com.messagesapp.data.remote.entities.PostsApi


@Database(entities = [PostsApi::class,Users::class, Comments::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostDao
}