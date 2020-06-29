package com.sourabhkarkal.blogreaderdemo.domain

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.repo.network.BlogApi
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class BlogSubscriber @Inject constructor(blogApi: BlogApi, scheduler: Scheduler) {

    private var blogApi: BlogApi = blogApi

    fun getBlogData (page: String, limit: String): Single<BlogResponseDTO> = blogApi.requestBlogApi(page, limit);

}