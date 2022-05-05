package com.messagesapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "users")
data class UsersApi(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @SerializedName("username")
    @ColumnInfo(name = "user_name")  val userName: String,
    @ColumnInfo(name = "email") val email: String,
    @SerializedName("website")
    @ColumnInfo(name = "web_site") val webSite: String
)