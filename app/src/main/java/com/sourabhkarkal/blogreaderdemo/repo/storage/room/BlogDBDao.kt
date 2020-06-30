package com.sourabhkarkal.blogreaderdemo.repo.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO
import io.reactivex.Single

@Dao
interface BlogDBDao {

    @Query("SELECT * FROM blog_table ORDER BY id ASC")
    fun loadAllArticle(): Single<List<BlogResponseDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(blogResponse: BlogResponseDTO)

}