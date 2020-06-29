package com.sourabhkarkal.blogreaderdemo.model

import java.io.Serializable

data class UserDTO(
	val createdAt: String? = null,
	val city: String? = null,
	val name: String? = null,
	val about: String? = null,
	val id: String? = null,
	val avatar: String? = null,
	val designation: String? = null,
	val blogId: String? = null,
	val lastname: String? = null
)