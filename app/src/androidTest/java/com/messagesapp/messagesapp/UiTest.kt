package com.messagesapp.messagesapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.messagesapp.posts.R.id.*
import com.messagesapp.posts.PostsActivity
import com.messagesapp.posts.adapters.PostListViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTest {

    @get:Rule
    val activityRuleSearch = ActivityScenarioRule(PostsActivity::class.java)

    @Test
    fun clickPostItemList() {

        Thread.sleep(5000)
        onView(withId(recycler_post))
            .perform(
                actionOnItemAtPosition<PostListViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
    }

    @Test
    fun flowPostFavorites(){
        Thread.sleep(1000)
        onView(withId(recycler_post))
            .perform(
                actionOnItemAtPosition<PostListViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        onView(withId(fab)).perform(click())
        Thread.sleep(2000)
        pressBack()
        onView(withId(recycler_post))
            .perform(
                actionOnItemAtPosition<PostListViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        onView(withId(fab2)).perform(click())
        Thread.sleep(2000)
        pressBack()
    }

    @Test
    fun flowToDeletePost(){
        Thread.sleep(2000)
        onView(withId(recycler_post))
            .perform(
                actionOnItemAtPosition<PostListViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Thread.sleep(2000)
        onView(withId(action_favorite)).perform(click())
    }
}