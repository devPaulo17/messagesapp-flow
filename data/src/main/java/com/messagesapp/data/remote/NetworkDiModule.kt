package com.messagesapp.data.remote

import androidx.room.Room
import com.messagesapp.data.local.AppDatabase
import com.messagesapp.data.local.LocalPostsDataSourceImpl
import com.messagesapp.data.remote.posts.RemotePostsDataSourceImpl
import com.messagesapp.data.remote.retrofit.ApiServices.PostsApiService
import com.messagesapp.domain.repositories.posts.LocalPostsDataSource
import com.messagesapp.domain.repositories.posts.RemotePostsDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "app.db"
        )
            .build()
    }
    single { provideRetrofit(get()) }

    factory { provideOkHttpClient(get()) }
    factory { providePostsApi(get()) }
    factory { provideLoggingInterceptor() }
    factory { get<AppDatabase>().postsDao() }


    factory<RemotePostsDataSource> {
        RemotePostsDataSourceImpl(get())
    }
    factory<LocalPostsDataSource> {
        LocalPostsDataSourceImpl(get())
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

fun providePostsApi(retrofit: Retrofit): PostsApiService =
    retrofit.create(PostsApiService::class.java)


