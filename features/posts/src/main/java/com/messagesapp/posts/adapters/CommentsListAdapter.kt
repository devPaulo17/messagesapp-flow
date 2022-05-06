package com.messagesapp.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.messagesapp.domain.entities.posts.Comments

class CommentsListAdapter : RecyclerView.Adapter<CommentsListViewHolder>() {

    var commentList = listOf<Comments>()
        private set

    fun setCommentsList(value: List<Comments>) {
        commentList = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentsListViewHolder(layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: CommentsListViewHolder, position: Int) {
        val item = commentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = commentList.size
}