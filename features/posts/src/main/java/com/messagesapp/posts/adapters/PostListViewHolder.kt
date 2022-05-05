package com.messagesapp.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.posts.databinding.ItemPostBinding

class PostListViewHolder(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup,
    private val binding: ItemPostBinding = ItemPostBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Posts, onResultItemClick: (Int) -> Unit) {
        binding.apply {
            textViewTitle.text = post.title
            containerItemPost.setOnClickListener {
                onResultItemClick.invoke(post.id)
            }
        }
    }
}