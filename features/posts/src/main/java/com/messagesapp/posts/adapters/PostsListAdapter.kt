package com.messagesapp.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.messagesapp.domain.entities.posts.Posts

class PostsListAdapter(private val onResultItemClick: (Int,Boolean,Int) -> Unit) : RecyclerView.Adapter<PostListViewHolder>() {

    var postsList = listOf<Posts>()
        private set

    fun setPostsList(value: List<Posts>) {
        postsList = value
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostListViewHolder(layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val item = postsList[position]
        holder.bind(item,onResultItemClick)
    }

    override fun getItemCount(): Int = postsList.size
}