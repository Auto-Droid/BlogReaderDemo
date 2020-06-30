package com.sourabhkarkal.blogreaderdemo.utils

import android.content.Context

object ContextProvider {
    private var applicationContext: Context? = null
    fun provideContext(): Context? {
        return applicationContext
    }

    fun setApplicationContext(context: Context?) {
        applicationContext = context
    }

    fun destroyContext() {
        applicationContext = null
    }
}