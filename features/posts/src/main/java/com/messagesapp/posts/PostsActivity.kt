package com.messagesapp.posts

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.posts.adapters.PostsListAdapter
import com.messagesapp.posts.databinding.ActivityPostsBinding
import com.messagesapp.posts.uistates.PostsUiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsActivity : AppCompatActivity() {

    private val postsViewModel: PostsViewModel by viewModel()
    private val onResultItemClick: (Int, Boolean, Int) -> Unit = { postId, isfavorite, userId ->
        goToPostDetail(postId, isfavorite, userId)
    }
    private var binding: ActivityPostsBinding? = null
    private var searchResultsListAdapter = PostsListAdapter(onResultItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setUpToolbar()
        searchResultsObserver()
        setUpRecyclerView()
        setSwipeRefreshLayout()
        postsViewModel.getAllPosts()
    }

    private fun setSwipeRefreshLayout() {

        binding?.apply {
            swipeToRefresh.setOnRefreshListener {
                postsViewModel.getAllPosts(true)
                swipeToRefresh.isRefreshing = false
            }
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.title = getString(R.string.posts_title)
    }

    private fun searchResultsObserver() {
        postsViewModel.viewState.observe(this, ::handleUiState)
    }

    private fun handleUiState(state: PostsUiState) {
        when (state) {
            is PostsUiState.PostsList -> setSearchData(state.data)
            is PostsUiState.Error -> showEmptyState()
            is PostsUiState.Loading -> showLoadingState()
            else -> {
                Toast.makeText(this, getString(R.string.message_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoadingState() {
        binding?.apply {
            progressBarPostList.visibility = View.VISIBLE
            imageEmptySate.visibility = View.GONE
            textViewEmptyStateLabel.visibility = View.GONE
        }
    }

    private fun showEmptyState() {
        binding?.apply {
            recyclerPost.visibility = View.GONE
            imageEmptySate.visibility = View.VISIBLE
            textViewEmptyStateLabel.visibility = View.VISIBLE
        }
    }

    private fun setUpRecyclerView() {
        binding?.recyclerPost?.apply {
            adapter = searchResultsListAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(context)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setSearchData(data: List<Posts>) {
        binding?.apply {
            recyclerPost.visibility = View.VISIBLE
            imageEmptySate.visibility = View.GONE
            textViewEmptyStateLabel.visibility = View.GONE
            progressBarPostList.visibility = View.GONE
        }
        searchResultsListAdapter.setPostsList(data)
        searchResultsListAdapter.notifyDataSetChanged()
    }

    private fun goToPostDetail(postId: Int, isfavorite: Boolean, userId: Int) {
        startActivity(Intent(this, PostDetailActivity::class.java).apply {
            putExtra(POST_ID, postId)
            putExtra(IS_FAVORITE, isfavorite)
            putExtra(USER_ID, userId)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_posts_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.refresh -> postsViewModel.getAllPosts(true)
            R.id.deleteAll -> postsViewModel.deleteAllPosts(true)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}