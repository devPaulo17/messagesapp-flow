package com.messagesapp.messagesapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        initializeDiKoin()
    }

    private fun initializeDiKoin(){
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            androidLogger(Level.ERROR)
            modules(MODULES_DI)
        }
    }
}