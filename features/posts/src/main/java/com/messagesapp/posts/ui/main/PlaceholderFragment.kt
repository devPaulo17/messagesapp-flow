package com.messagesapp.posts.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.UserPost
import com.messagesapp.posts.PostsViewModel
import com.messagesapp.posts.adapters.CommentsListAdapter
import com.messagesapp.posts.databinding.FragmentPostDetailBinding
import com.messagesapp.posts.uistates.PostsUiState

const val USER_INFO_TAB = 1

class PlaceholderFragment : Fragment() {

    private val binding get() = _binding!!
    private val postsViewModel: PostsViewModel by activityViewModels()
    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentPostDetailBinding? = null
    private var commentsResultsListAdapter = CommentsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this)[PageViewModel::class.java].apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        val root = binding.root

        pageViewModel.fragmentId.observe(viewLifecycleOwner) { tabId ->
            setInfoTab(tabId)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultsObserver()
        setUpRecyclerView()
    }

    private fun setInfoTab(tabId: Int) {
        if (tabId == USER_INFO_TAB) {
            binding.constraintUserData.visibility = View.VISIBLE
            binding.recyclerPostComments.visibility = View.GONE
        } else {
            binding.constraintUserData.visibility = View.GONE
            binding.recyclerPostComments.visibility = View.VISIBLE
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerPostComments.apply {
            adapter = commentsResultsListAdapter
            itemAnimator = null
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun searchResultsObserver() {
        postsViewModel.viewState.observe(viewLifecycleOwner, ::handleUiState)
    }

    private fun handleUiState(state: PostsUiState) {
        when (state) {
            is PostsUiState.PostDetail -> setSearchData(state.data)
            is PostsUiState.PostComments -> setPostComments(state.data)
            is PostsUiState.Ids -> {
                postsViewModel.getPostDetail(state.data["userId"]!!)
                postsViewModel.getComments(state.data["postId"]!!)
            }
            else -> {
                Toast.makeText(context,"Hubo un error",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSearchData(data: UserPost) {
        binding.apply {
            textViewPostDescription.text = data.postBoddy
            textViewUserName.text = data.userName
            textViewUserEmail.text = data.userEmail
            textViewUserNickname.text = data.userNickName
            textViewUserWebsite.text = data.userWebSite
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setPostComments(data: List<Comments>) {
        commentsResultsListAdapter.setCommentsList(data)
        commentsResultsListAdapter.notifyDataSetChanged()
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}