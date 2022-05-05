package com.messagesapp.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.messagesapp.posts.databinding.ActivityPostsBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailActivity : AppCompatActivity() {


    private var binding: ActivityPostsBinding? = null
    private val postsViewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        postsViewModel.getPostDetail()
    }
}