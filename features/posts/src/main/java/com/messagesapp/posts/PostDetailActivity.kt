package com.messagesapp.posts

import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.messagesapp.posts.ui.main.SectionsPagerAdapter
import com.messagesapp.posts.databinding.ActivityPostDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding
    private val postsViewModel: PostsViewModel by viewModel()
    private lateinit var fab: FloatingActionButton
    private lateinit var fab2: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPagerAdapter()
        val postId = intent.getIntExtra("postId", 0)
        val isFavorite = intent.getBooleanExtra("isFavorite", false)

        postsViewModel.setPostId(postId)

        fab = binding.fab
        fab2 = binding.fab2

        if (!isFavorite) {
            removeFromFavorites(postId)
        } else {
            addToFavorites(postId)
        }
    }

    private fun setPagerAdapter() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }

    private fun addToFavorites(postId: Int) {
        fab2.visibility = View.VISIBLE
        fab2.setOnClickListener { view ->
            postsViewModel.deletePostFromFavorites(postId)
            fab2.visibility = View.GONE
            fab.visibility = View.VISIBLE
            Snackbar.make(view, "Post removed from favorites", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            removeFromFavorites(postId)
        }
    }

    private fun removeFromFavorites(postId: Int) {
        fab.visibility = View.VISIBLE
        fab.setOnClickListener { view ->
            postsViewModel.addPostToFavorites(postId)
            fab2.visibility = View.VISIBLE
            fab.visibility = View.GONE
            Snackbar.make(view, "Post save as favorite", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            addToFavorites(postId)
        }
    }
}