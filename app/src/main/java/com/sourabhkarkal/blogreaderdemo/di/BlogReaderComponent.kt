package com.sourabhkarkal.blogreaderdemo.di

import com.sourabhkarkal.blogreaderdemo.di.api.BlogSubscriberModule
import com.sourabhkarkal.blogreaderdemo.di.network.NetworkComponent
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import com.sourabhkarkal.blogreaderdemo.di.scope.AppScope
import com.sourabhkarkal.blogreaderdemo.repo.BlogApiCall
import com.sourabhkarkal.blogreaderdemo.viewmodel.BlogFragmentViewModel
import dagger.Component

@AppScope
@Component(dependencies = [NetworkComponent::class],  modules = [BlogSubscriberModule::class])
interface BlogReaderComponent {

    fun scheduler(): Scheduler?
    fun blogApiCall(): BlogApiCall?

    fun inject(blogViewModel: BlogFragmentViewModel)
    
}