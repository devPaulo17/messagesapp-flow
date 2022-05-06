package com.messagesapp.posts

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.messagesapp.posts.databinding.ActivityPostDetailBinding
import com.messagesapp.posts.ui.main.SectionsPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailActivity : AppCompatActivity() {

    private val postsViewModel: PostsViewModel by viewModel()
    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var fab: FloatingActionButton
    private lateinit var fab2: FloatingActionButton
    private var postId = DEFAULT_VALUE
    private var userId = DEFAULT_VALUE
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()
        setPagerAdapter()
        getIntentExtras()
        setIdsPostAndUser()
        setActionButtonFab()
    }

    private fun getIntentExtras() {
        postId = intent.getIntExtra(POST_ID, DEFAULT_VALUE)
        isFavorite = intent.getBooleanExtra(IS_FAVORITE, false)
        userId = intent.getIntExtra(USER_ID, DEFAULT_VALUE)
    }

    private fun setIdsPostAndUser() {
        val hashMapIds = hashMapOf(USER_ID to userId, POST_ID to postId)
        postsViewModel.setIdsPostAndUser(hashMapIds)
    }

    private fun setActionButtonFab() {
        fab = binding.fabButtonAddFavorite
        fab2 = binding.fabButtonRemoveFavorite

        if (!isFavorite) {
            removeFromFavorites(postId)
        } else {
            addToFavorites(postId)
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.title = getString(R.string.post_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            Snackbar.make(view, getString(R.string.post_removed_label), Snackbar.LENGTH_LONG).show()
            removeFromFavorites(postId)
        }
    }

    private fun removeFromFavorites(postId: Int) {
        fab.visibility = View.VISIBLE
        fab.setOnClickListener { view ->
            postsViewModel.addPostToFavorites(postId)
            fab2.visibility = View.VISIBLE
            fab.visibility = View.GONE
            Snackbar.make(view, getString(R.string.post_add_favorites_label), Snackbar.LENGTH_LONG)
                .show()
            addToFavorites(postId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_detail_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_favorite ->{
                postsViewModel.deletePostById(postId)
                Toast.makeText(this, getString(R.string.post_deteleted_label), Toast.LENGTH_LONG)
                    .show()
                finish()
            }
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}