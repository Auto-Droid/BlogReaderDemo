package com.sourabhkarkal.blogreaderdemo.repo

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import io.reactivex.Single
import retrofit2.Call

interface BlogApiCall {

    fun getBlogApiResponse(page: String, limit: String): Single<List<BlogResponseDTO>>?
}