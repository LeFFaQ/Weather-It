/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.network.models.onecall

import com.google.gson.annotations.SerializedName

data class OneCallModel(

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("timezone_offset")
	val timezoneOffset: Int? = null,

	@field:SerializedName("daily")
	val daily: List<DailyItem?>? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class DailyItem(

	@field:SerializedName("moonset")
	val moonset: Number? = null,

	@field:SerializedName("sunrise")
	val sunrise: Number? = null,

	@field:SerializedName("temp")
	val temp: Temp? = null,

	@field:SerializedName("moon_phase")
	val moonPhase: Number? = null,

	@field:SerializedName("uvi")
	val uvi: Number? = null,

	@field:SerializedName("moonrise")
	val moonrise: Number? = null,

	@field:SerializedName("pressure")
	val pressure: Number? = null,

	@field:SerializedName("clouds")
	val clouds: Number? = null,

	@field:SerializedName("feels_like")
	val feelsLike: FeelsLike? = null,

	@field:SerializedName("wind_gust")
	val windGust: Number? = null,

	@field:SerializedName("dt")
	val dt: Number? = null,

	@field:SerializedName("pop")
	val pop: Number? = null,

	@field:SerializedName("wind_deg")
	val windDeg: Number? = null,

	@field:SerializedName("dew_point")
	val dewPoint: Number? = null,

	@field:SerializedName("sunset")
	val sunset: Int? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

	@field:SerializedName("humidity")
	val humidity: Number? = null,

	@field:SerializedName("wind_speed")
	val windSpeed: Number? = null,

	@field:SerializedName("rain")
	val rain: Number? = null
)

data class Temp(

	@field:SerializedName("min")
	val min: Number? = null,

	@field:SerializedName("max")
	val max: Number? = null,

	@field:SerializedName("eve")
	val eve: Number? = null,

	@field:SerializedName("night")
	val night: Number? = null,

	@field:SerializedName("day")
	val day: Number? = null,

	@field:SerializedName("morn")
	val morn: Number? = null
)

data class FeelsLike(

	@field:SerializedName("eve")
	val eve: Number? = null,

	@field:SerializedName("night")
	val night: Number? = null,

	@field:SerializedName("day")
	val day: Number? = null,

	@field:SerializedName("morn")
	val morn: Number? = null
)

data class WeatherItem(

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("main")
	val main: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
