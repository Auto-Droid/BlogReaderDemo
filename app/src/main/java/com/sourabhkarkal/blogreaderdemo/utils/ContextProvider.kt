package com.sourabhkarkal.blogreaderdemo.utils

import android.content.Context

class ContextProvider private constructor() {
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

    companion object {
        private var mContextProvider: ContextProvider? = null
        val contextProvider: ContextProvider?
            get() {
                if (mContextProvider == null) {
                    synchronized(
                        ContextProvider::class.java
                    ) { mContextProvider = ContextProvider() }
                }
                return mContextProvider
            }
    }
}