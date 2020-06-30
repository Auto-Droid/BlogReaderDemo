package com.sourabhkarkal.blogreaderdemo

import android.app.Application
import android.content.Context
import com.sourabhkarkal.blogreaderdemo.di.BlogReaderComponent
import com.sourabhkarkal.blogreaderdemo.di.DaggerBlogReaderComponent
import com.sourabhkarkal.blogreaderdemo.di.api.BlogSubscriberModule
import com.sourabhkarkal.blogreaderdemo.di.network.DaggerNetworkComponent
import com.sourabhkarkal.blogreaderdemo.utils.ContextProvider

class BlogApplication : Application() {

    companion object {
        lateinit var appComponent: BlogReaderComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerBlogReaderComponent.builder()
            .blogSubscriberModule(BlogSubscriberModule(this))
            .networkComponent(DaggerNetworkComponent.builder().build())
            .build()

        ContextProvider.setApplicationContext(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ContextProvider.destroyContext()
    }

}