package com.messagesapp.data.local.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.messagesapp.data.local.entities.Comments
import com.messagesapp.data.local.entities.Posts
import com.messagesapp.data.local.entities.Users
import com.messagesapp.data.remote.entities.PostsApi
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePosts(posts: List<PostsApi>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUsers(posts: List<Users>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCommentsByPostId(posts: List<Comments>)

    @Query("SELECT * FROM posts")
    fun getAllDogs(): Flow<List<PostsApi>>

    @Query(
        "SELECT users.name AS userName, posts.title AS bookName " +
                "FROM users, posts " +
                "WHERE users.id = posts.user_id"
    )
    fun getPostDetailById(): Flow<UserBook>

}

data class UserBook(val userName: String?, val bookName: String?)