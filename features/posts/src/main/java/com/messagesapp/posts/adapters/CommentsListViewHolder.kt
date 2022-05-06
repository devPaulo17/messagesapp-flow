package com.messagesapp.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.posts.databinding.ItemCommentBinding

class CommentsListViewHolder(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup,
    private val binding: ItemCommentBinding = ItemCommentBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Comments) {
        binding.apply {
            textViewCommentBody.text = post.body
            textViewUserEmailComment.text = post.email
        }
    }
}