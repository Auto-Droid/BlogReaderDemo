package com.sourabhkarkal.blogreaderdemo.domain

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.repo.BlogApiCall
import com.sourabhkarkal.blogreaderdemo.scheduler.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class BlogSubscriber @Inject constructor(private val blogApiCall: BlogApiCall, val scheduler: Scheduler) {

    fun getBlogData (page: String, limit: String): Single<List<BlogResponseDTO>> =
        blogApiCall.getBlogApiResponse(page, limit)!!;

}