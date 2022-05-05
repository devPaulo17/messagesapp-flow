package com.messagesapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "posts")
data class PostsApi(
    @ColumnInfo(name = "user_id") val userId: Int,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "boddy") val body: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false
)