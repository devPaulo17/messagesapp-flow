package com.messagesapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "web_site") val webSite: String
)