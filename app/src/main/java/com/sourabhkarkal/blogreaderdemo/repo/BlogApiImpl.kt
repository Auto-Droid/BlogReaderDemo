package com.sourabhkarkal.blogreaderdemo.repo

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.repo.network.BlogApi
import io.reactivex.Single
import javax.inject.Inject

class BlogApiImpl @Inject constructor(blogApi: BlogApi?) : BlogApiCall {

    override fun getBlogApiResponse(): Single<BlogResponseDTO?>? {
        TODO("Not yet implemented")
    }


}