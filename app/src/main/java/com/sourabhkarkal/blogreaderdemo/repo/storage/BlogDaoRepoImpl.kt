package com.sourabhkarkal.blogreaderdemo.repo.storage

import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import com.sourabhkarkal.blogreaderdemo.repo.storage.room.BlogDatabase
import io.reactivex.Single
import javax.inject.Inject

class BlogDaoRepoImpl @Inject constructor(blogDatabase: BlogDatabase?): BlogDaoRepo {

    private var db: BlogDatabase? = blogDatabase

    override fun getAllArticle(): Single<List<BlogResponseDTO>>? {
        return db?.blogDao()?.loadAllArticle()
    }

    override fun insertArticle(blogResponse: List<BlogResponseDTO>) {
        for(blog in blogResponse) {
            db?.blogDao()?.insert(blog)
        }
    }
}