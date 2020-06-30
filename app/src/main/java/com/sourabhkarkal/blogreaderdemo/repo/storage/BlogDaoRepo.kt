package com.sourabhkarkal.blogreaderdemo.repo.storage

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import io.reactivex.Single

interface BlogDaoRepo {

    fun getAllArticle(): Single<List<BlogResponseDTO>>?

    fun insertArticle(blogResponse: List<BlogResponseDTO>)

}