package com.messagesapp.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.posts.adapters.PostsListAdapter
import com.messagesapp.posts.databinding.ActivityPostsBinding
import com.messagesapp.posts.uistates.PostsUiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsActivity : AppCompatActivity() {

    private var binding: ActivityPostsBinding? = null

    private val postsViewModel: PostsViewModel by viewModel()

    private val onResultItemClick: (Int) -> Unit = { postId ->
        goToPostDetail(postId)
    }

    private var searchResultsListAdapter = PostsListAdapter(onResultItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        searchResultsObserver()
        setUpRecyclerView()
        postsViewModel.getAllPosts()


    }

    private fun searchResultsObserver() {
        postsViewModel.viewState.observe(this, ::handleUiState)
    }

    private fun handleUiState(state: PostsUiState) {
        when (state) {
            is PostsUiState.PostsList -> setSearchData(state.data)
            else -> {
                println("sdasd")
            }
        }
    }


    private fun setUpRecyclerView() {
        binding?.recylerViewPosts?.apply {
            adapter = searchResultsListAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(context)
        }
    }


    private fun setSearchData(data: List<Posts>) {
        searchResultsListAdapter.setPostsList(data)
        searchResultsListAdapter.notifyDataSetChanged()
    }

    private fun goToPostDetail(postId: Int) {
        startActivity(Intent(this, PostDetailActivity::class.java))
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}