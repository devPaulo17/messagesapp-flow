package com.messagesapp.data.remote.retrofit

import com.messagesapp.domain.HandleResult
import retrofit2.Response

internal inline fun <T : Any> executeRetrofitRequest(request: () -> Response<T>): HandleResult<T> {
    return try {
        val response = request.invoke()
        return if (response.isSuccessful && response.body() != null) {
            val body = response.body()
            if (body != null) {
                HandleResult.Success(body)
            } else {
                HandleResult.Error("Empty body found in this request")
            }
        } else {
            val errorBody = response.errorBody()
            val errorText = errorBody?.string() ?: "Error body null"
            HandleResult.Error(errorText, response.code())
        }
    }
    catch (ex: Exception) {
        HandleResult.Error(ex.message)
    }
}

fun <Api : Any, Data : Any> handleResultRetrofit(
    result: HandleResult<Api>,
    onSuccess: (Api) -> Data
): HandleResult<Data> {
    return when (result) {
        is HandleResult.Error -> {
            if(result.exception?.contains("Unable to resolve host") == true) {
                HandleResult.InternetConnectionError
            } else {
                result
            }
        }
        is HandleResult.Success -> HandleResult.Success(onSuccess.invoke(result.data))
        else -> HandleResult.Loading
    }
}