package com.sourabhkarkal.blogreaderdemo.model

import java.io.Serializable

data class MediaDTO(
	val createdAt: String? = null,
	val image: String? = null,
	val id: String? = null,
	val title: String? = null,
	val blogId: String? = null,
	val url: String? = null
)