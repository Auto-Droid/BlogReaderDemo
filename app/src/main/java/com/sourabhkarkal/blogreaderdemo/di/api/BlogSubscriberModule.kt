package com.sourabhkarkal.blogreaderdemo.di.api

import com.sourabhkarkal.blogreaderdemo.di.scope.AppScope
import com.sourabhkarkal.blogreaderdemo.repo.BlogApiCall
import com.sourabhkarkal.blogreaderdemo.repo.BlogApiImpl
import com.sourabhkarkal.blogreaderdemo.repo.network.BlogApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BlogSubscriberModule {

    @AppScope
    @Provides
    fun provideRepoIntf(blogApi: BlogApi?): BlogApiCall? {
        return BlogApiImpl(blogApi)
    }

    @AppScope
    @Provides
    fun provideBlogCallerApi(retrofit: Retrofit): BlogApi? {
        return retrofit.create<BlogApi>(BlogApi::class.java)
    }

}