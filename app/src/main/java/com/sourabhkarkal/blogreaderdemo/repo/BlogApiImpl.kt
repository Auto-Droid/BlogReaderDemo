package com.sourabhkarkal.blogreaderdemo.repo

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.repo.network.BlogApi
import com.sourabhkarkal.blogreaderdemo.repo.storage.BlogDaoRepo
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BlogApiImpl @Inject constructor(
    private val blogApi: BlogApi?,
    var blogDaoRepo: BlogDaoRepo?
) : BlogApiCall {

    override fun getBlogApiResponse(page: String, limit: String): Single<List<BlogResponseDTO>>? {
        return blogApi?.requestBlogApi(page = page, limit = limit)?.subscribeOn(Schedulers.io())
            ?.flatMap {
                if (it.isNotEmpty()) {
                    blogDaoRepo?.insertArticle(it);
                }
                Single.just(it)
            }
    }
}


