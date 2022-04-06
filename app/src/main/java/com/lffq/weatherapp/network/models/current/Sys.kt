package com.lffq.weatherapp.network.models.current

import com.google.gson.annotations.SerializedName

data class Sys(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("sunrise")
	val sunrise: Int,

	@field:SerializedName("sunset")
	val sunset: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: Int,

	@field:SerializedName("message")
	val message: Double
)