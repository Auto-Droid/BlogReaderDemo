package com.sourabhkarkal.blogreaderdemo.di.network

import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ThreadModule::class])
interface NetworkComponent {
    fun retrofit(): Retrofit
    fun scheduler(): Scheduler
}