package com.sourabhkarkal.blogreaderdemo.repo.storage.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.sourabhkarkal.blogreaderdemo.model.BlogResponseDTO

@Database(entities = [BlogResponseDTO::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class BlogDatabase : RoomDatabase() {

    abstract fun blogDao(): BlogDBDao

    companion object {
        private var INSTANCE: BlogDatabase? = null

        fun getInstance(context: Context): BlogDatabase? {
            if (INSTANCE == null) {
                synchronized(BlogDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, BlogDatabase::class.java, "BlogDB")
                            .build()
                }
            }
            return INSTANCE
        }
    }
}
