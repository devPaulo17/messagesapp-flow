package com.messagesapp.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.messagesapp.domain.HandleResult
import com.messagesapp.domain.entities.posts.Comments
import com.messagesapp.domain.entities.posts.Posts
import com.messagesapp.domain.entities.posts.UserPost
import com.messagesapp.domain.repositories.posts.PostsRepository
import com.messagesapp.posts.uistates.PostsUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import kotlin.test.assertTrue

class PostViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    private val posts =
        listOf(Posts(1, 1, "test", "asdasd", false), Posts(1, 1, "test", "asdasd", false))

    private val comments: List<Comments> =
        listOf(Comments(1, 1, "Some Random Email", "test@gmail.com", "asdasds"))

    private val postDetailData: UserPost =
        UserPost("Paulo", "test@gmail.com", "Some Random Email", "dasdasd", "asdasds")


    private val mockResultsRepository = mock<PostsRepository> {
        onBlocking { getAllPosts(false) } doReturn flow { emit(HandleResult.Success(posts)) }
        onBlocking { getComments(1) } doReturn flow { emit(HandleResult.Success(comments)) }
        onBlocking { getPostDetail(1) } doReturn flow { emit(HandleResult.Success(postDetailData)) }
    }

    private val viewModel =
        PostsViewModel(mockResultsRepository)

    @Test
    fun `getAllPost() when use case returns HandleResult Success then state emit  results`() {
        val spyLiveData: Observer<PostsUiState> = spy(Observer { })
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.getAllPosts(true)
            assertTrue(viewModel.viewState.value is PostsUiState.PostsList)
        }
    }

    @Test
    fun `getCommentsByPostId() when use case returns HandleResult Success then state emit  results`() {
        val spyLiveData: Observer<PostsUiState> = spy(Observer { })
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.getComments(1)
            assertTrue(viewModel.viewState.value is PostsUiState.PostComments)
        }
    }

    @Test
    fun `getPostDetail() when use case returns HandleResult Success then state emit  results`() {
        val spyLiveData: Observer<PostsUiState> = spy(Observer { })
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.getPostDetail(1)
            assertTrue(viewModel.viewState.value is PostsUiState.PostDetail)
        }
    }
}