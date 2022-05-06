package com.messagesapp.posts.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.UserPost
import com.messagesapp.posts.PostsViewModel
import com.messagesapp.posts.adapters.CommentsListAdapter
import com.messagesapp.posts.databinding.FragmentPostDetailBinding
import com.messagesapp.posts.uistates.PostsUiState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

const val USER_INFO_TAB = 1
const val POST_COMMENTS_TAB = 2

class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentPostDetailBinding? = null

    private  val postsViewModel: PostsViewModel by activityViewModels()
    private var commentsResultsListAdapter = CommentsListAdapter()

    private val binding get() = _binding!!

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

        pageViewModel.fragmentId.observe(viewLifecycleOwner, Observer {
            if (it == USER_INFO_TAB) {
                binding.constraintUserData.visibility = View.VISIBLE
                binding.recyclerPostComments.visibility = View.GONE
            } else {
                binding.constraintUserData.visibility = View.GONE
                binding.recyclerPostComments.visibility = View.VISIBLE
            }
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultsObserver()
        setUpRecyclerView()
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
            is PostsUiState.PostId ->{
                postsViewModel.getPostDetail(state.data["userId"]!!)
                postsViewModel.getComments(state.data["postId"]!!)
            }
            else -> {
                println("sdasd")
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

    private fun setPostComments(data: List<Comments>) {
        commentsResultsListAdapter.setCommentsList(data)
        commentsResultsListAdapter.notifyDataSetChanged()
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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