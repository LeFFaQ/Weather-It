package com.lffq.weatherapp.network.models.current

import com.google.gson.annotations.SerializedName

data class Clouds(

	@field:SerializedName("all")
	val all: Int
)