package com.messagesapp.messagesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.messagesapp.posts.PostsActivity
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(this@SplashScreenActivity, PostsActivity::class.java))
            }
        }, 1000)
    }
}