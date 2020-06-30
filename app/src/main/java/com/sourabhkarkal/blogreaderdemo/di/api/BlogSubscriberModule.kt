package com.sourabhkarkal.blogreaderdemo.di.api

import android.app.Application
import com.sourabhkarkal.blogreaderdemo.di.scope.AppScope
import com.sourabhkarkal.blogreaderdemo.repo.BlogApiCall
import com.sourabhkarkal.blogreaderdemo.repo.BlogApiImpl
import com.sourabhkarkal.blogreaderdemo.repo.network.BlogApi
import com.sourabhkarkal.blogreaderdemo.repo.storage.BlogDaoRepo
import com.sourabhkarkal.blogreaderdemo.repo.storage.BlogDaoRepoImpl
import com.sourabhkarkal.blogreaderdemo.repo.storage.room.BlogDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BlogSubscriberModule constructor(var context: Application){


    @AppScope
    @Provides
    fun provideRepoIntf(blogApi: BlogApi?,blogDaoRepo: BlogDaoRepo?): BlogApiCall {
        return BlogApiImpl(blogApi, blogDaoRepo)
    }

    @AppScope
    @Provides
    fun provideBlogCallerApi(retrofit: Retrofit): BlogApi {
        return retrofit.create<BlogApi>(BlogApi::class.java)
    }

    @AppScope
    @Provides
    fun provideBlogDao(): BlogDaoRepo {
        return BlogDaoRepoImpl(BlogDatabase.getInstance(context))
    }

}