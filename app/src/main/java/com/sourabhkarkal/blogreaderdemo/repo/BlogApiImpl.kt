package com.sourabhkarkal.blogreaderdemo.repo

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.repo.network.BlogApi
import io.reactivex.Single
import javax.inject.Inject

class BlogApiImpl @Inject constructor(private val blogApi: BlogApi?) : BlogApiCall {

    override fun getBlogApiResponse(page: String, limit: String): Single<List<BlogResponseDTO>>? {
        return blogApi?.requestBlogApi(page = page, limit = limit)
    }


}