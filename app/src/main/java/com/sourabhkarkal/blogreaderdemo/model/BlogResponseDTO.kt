package com.sourabhkarkal.blogreaderdemo.model

import java.io.Serializable

data class BlogResponseDTO(
	val createdAt: String? = null,
	val comments: Int? = null,
	val id: String? = null,
	val media: List<MediaDTO?>? = null,
	val user: List<UserDTO?>? = null,
	val content: String? = null,
	val likes: Int? = null
)