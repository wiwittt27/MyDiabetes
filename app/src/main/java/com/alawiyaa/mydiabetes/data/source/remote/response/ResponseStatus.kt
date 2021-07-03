package com.alawiyaa.mydiabetes.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseStatus(
	@field:SerializedName("status")
	val status: String? = null
)
