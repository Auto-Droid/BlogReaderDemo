package com.sourabhkarkal.blogreaderdemo

import android.app.Application
import com.sourabhkarkal.blogreaderdemo.di.BlogReaderComponent
import com.sourabhkarkal.blogreaderdemo.di.DaggerBlogReaderComponent
import com.sourabhkarkal.blogreaderdemo.di.api.BlogSubscriberModule
import com.sourabhkarkal.blogreaderdemo.di.network.DaggerNetworkComponent

class BlogApplication : Application() {

    companion object {
        lateinit var appComponent: BlogReaderComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerBlogReaderComponent.builder()
            .blogSubscriberModule(BlogSubscriberModule())
            .networkComponent(DaggerNetworkComponent.builder().build())
            .build()
    }

}