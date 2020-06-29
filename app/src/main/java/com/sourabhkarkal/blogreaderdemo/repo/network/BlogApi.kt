package com.sourabhkarkal.blogreaderdemo.repo.network

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogApi {

    @GET("/jet2/api/v1/blogs")
    fun requestBlogApi(@Query("page")page : String, @Query("limit") limit: String) : Single<List<BlogResponseDTO>>
}