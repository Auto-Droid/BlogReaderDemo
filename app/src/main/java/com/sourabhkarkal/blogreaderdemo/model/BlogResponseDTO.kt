package com.sourabhkarkal.blogreaderdemo.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_table")
data class BlogResponseDTO(
	val createdAt: String? = null,
	val comments: Int? = null,

	@PrimaryKey
	@NonNull
	val id: String,
	val media: List<MediaDTO?>? = null,
	val user: List<UserDTO?>? = null,
	val content: String? = null,
	val likes: Int? = null
)