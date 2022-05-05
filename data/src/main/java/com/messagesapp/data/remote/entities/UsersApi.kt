package com.messagesapp.data.remote.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UsersApi(
    val id: Int,
    val name: String,
    @SerializedName("username")
    val userName: String,
    val email: String,
    @SerializedName("website")
    val webSite: String
)