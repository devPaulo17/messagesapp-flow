package com.messagesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.messagesapp.data.local.Daos.PostDao
import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
import com.messagesapp.data.entities.UsersApi


@Database(entities = [PostsApi::class,UsersApi::class, CommentsApi::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostDao
}