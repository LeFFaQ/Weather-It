package com.lffq.weatherapp.network.models.current

import com.google.gson.annotations.SerializedName

data class Main(

	@field:SerializedName("temp")
	val temp: Number,

	@field:SerializedName("temp_min")
	val tempMin: Number,

	@field:SerializedName("humidity")
	val humidity: Number,

	@field:SerializedName("pressure")
	val pressure: Number,

	@field:SerializedName("feels_like")
	val feelsLike: Number,

	@field:SerializedName("temp_max")
	val tempMax: Number
)