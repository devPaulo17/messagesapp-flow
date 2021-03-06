package com.messagesapp.posts.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val fragmentId: LiveData<Int> = Transformations.map(_index) { tabId ->
        tabId
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}