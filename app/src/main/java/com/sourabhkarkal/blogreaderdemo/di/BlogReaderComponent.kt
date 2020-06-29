package com.sourabhkarkal.blogreaderdemo.di

import com.sourabhkarkal.blogreaderdemo.di.network.NetworkComponent
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import com.sourabhkarkal.blogreaderdemo.scope.AppScope
import dagger.Component

@AppScope
@Component(dependencies = [NetworkComponent::class])
interface BlogReaderComponent {

    fun scheduler(): Scheduler?
    
}