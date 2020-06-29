package com.sourabhkarkal.blogreaderdemo.di.network

import com.sourabhkarkal.blogreaderdemo.scheduler.AppScheduler
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ThreadModule {
    @Singleton
    @Provides
    fun provideScheduler(): Scheduler {
        return AppScheduler()
    }
}