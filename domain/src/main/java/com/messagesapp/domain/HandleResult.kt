package com.messagesapp.domain

sealed class HandleResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : HandleResult<T>()
    data class Error(val exception: String?, val errorCode: Int = 0) : HandleResult<Nothing>()
    object InternetConnectionError : HandleResult<Nothing>()
    object Loading : HandleResult<Nothing>()
}
