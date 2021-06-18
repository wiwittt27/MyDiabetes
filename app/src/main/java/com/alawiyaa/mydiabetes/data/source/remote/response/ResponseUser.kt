package com.alawiyaa.mydiabetes.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseUser(
	@field:SerializedName("status")
	val status: String,


	@field:SerializedName("id_user")
	val userId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("level")
	val level: String

)
