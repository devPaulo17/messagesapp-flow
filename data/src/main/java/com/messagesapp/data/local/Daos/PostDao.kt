package com.messagesapp.data.local.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.messagesapp.data.entities.CommentsApi
import com.messagesapp.data.entities.PostsApi
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

    @Query("SELECT * FROM posts  ORDER BY is_favorite DESC")
    fun getAllDogs(): Flow<List<PostsApi>>

    @Query(
        "SELECT users.name AS userName, posts.title AS bookName " +
                "FROM users, posts " +
                "WHERE users.id = posts.user_id"
    )
    fun getPostDetailById(): Flow<UserBook>

    @Query("DELETE FROM posts")
    fun deleteAllPosts()

    @Query("DELETE FROM posts WHERE id = :postId")
    fun deletePostById(postId: Int)

    @Query("UPDATE posts SET is_favorite = 1 WHERE id = :postId")
    fun addPostToFavorites(postId: Int)

    @Query("UPDATE posts SET is_favorite = 0 WHERE id = :postId")
    fun remotePostFromFavorites(postId: Int)

}

data class UserBook(val userName: String?, val bookName: String?)