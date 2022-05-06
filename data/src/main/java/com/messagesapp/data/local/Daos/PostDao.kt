package com.messagesapp.data.local.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
import com.messagesapp.data.entities.UserPost
import com.messagesapp.data.entities.UsersApi
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePosts(posts: List<PostsApi>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUsers(posts: List<UsersApi>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCommentsByPostId(posts: List<CommentsApi>)

    @Query("SELECT * FROM comments WHERE post_id = :postId")
    fun getCommentsByPostId(postId: Int): Flow<List<CommentsApi>>

    @Query("SELECT * FROM posts  ORDER BY is_favorite DESC")
    fun getAllPosts(): Flow<List<PostsApi>>

    @Query(
        "SELECT users.name AS userName, users.email AS userEmail, users.user_name AS userNickName,users.web_site AS userWebSite, posts.boddy AS postBoddy " +
                "FROM users, posts " +
                "WHERE users.id = :postId"
    )
    fun getPostDetailById(postId: Int): Flow<UserPost>

    @Query("DELETE FROM posts")
    fun deleteAllPosts()

    @Query("DELETE FROM posts WHERE id = :postId")
    fun deletePostById(postId: Int)

    @Query("UPDATE posts SET is_favorite = 1 WHERE id = :postId")
    fun addPostToFavorites(postId: Int)

    @Query("UPDATE posts SET is_favorite = 0 WHERE id = :postId")
    fun removePostFromFavorites(postId: Int)

}

