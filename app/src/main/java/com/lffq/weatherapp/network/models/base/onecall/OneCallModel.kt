/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.network.models.base.onecall

import androidx.datastore.preferences.core.intPreferencesKey
import com.google.gson.annotations.SerializedName
import com.lffq.weatherapp.network.models.base.current.WeatherItem

data class OneCallModel(

	@field:SerializedName("current")
	val current: Current,

	@field:SerializedName("timezone")
	val timezone: String,

	@field:SerializedName("timezone_offset")
	val timezoneOffset: Int,

	@field:SerializedName("daily")
	val daily: List<DailyItem>,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("lat")
	val lat: Double
)

data class Current(

	@field:SerializedName("sunrise")
	val sunrise: Int,

	@field:SerializedName("temp")
	val temp: Number,

	@field:SerializedName("visibility")
	val visibility: Number,

	@field:SerializedName("uvi")
	val uvi: Number,

	@field:SerializedName("pressure")
	val pressure: Number,

	@field:SerializedName("clouds")
	val clouds: Number,

	@field:SerializedName("feels_like")
	val feelsLike: Number,

	@field:SerializedName("wind_gust")
	val windGust: Number,

	@field:SerializedName("dt")
	val dt: Int,

	@field:SerializedName("wind_deg")
	val windDeg: Number,

	@field:SerializedName("dew_point")
	val dewPoint: Number,

	@field:SerializedName("sunset")
	val sunset: Number,

	@field:SerializedName("weather")
	val weather: List<WeatherItem>,

	@field:SerializedName("humidity")
	val humidity: Number,

	@field:SerializedName("wind_speed")
	val windSpeed: Number
)

data class DailyItem(

	@field:SerializedName("moonset")
	val moonset: Number,

	@field:SerializedName("sunrise")
	val sunrise: Number,

	@field:SerializedName("temp")
	val temp: Temp,

	@field:SerializedName("moon_phase")
	val moonPhase: Number,

	@field:SerializedName("uvi")
	val uvi: Number,

	@field:SerializedName("moonrise")
	val moonrise: Number,

	@field:SerializedName("pressure")
	val pressure: Number,

	@field:SerializedName("clouds")
	val clouds: Number,

	@field:SerializedName("feels_like")
	val feelsLike: FeelsLike,

	@field:SerializedName("wind_gust")
	val windGust: Number,

	@field:SerializedName("dt")
	val dt: Long,

	@field:SerializedName("pop")
	val pop: Number,

	@field:SerializedName("wind_deg")
	val windDeg: Number,

	@field:SerializedName("dew_point")
	val dewPoint: Number,

	@field:SerializedName("sunset")
	val sunset: Number,

	@field:SerializedName("weather")
	val weather: List<WeatherItem>,

	@field:SerializedName("humidity")
	val humidity: Number,

	@field:SerializedName("wind_speed")
	val windSpeed: Number
)

data class Temp(

	@field:SerializedName("min")
	val min: Number,

	@field:SerializedName("max")
	val max: Number,

	@field:SerializedName("eve")
	val eve: Number,

	@field:SerializedName("night")
	val night: Number,

	@field:SerializedName("day")
	val day: Number,

	@field:SerializedName("morn")
	val morn: Number
)

data class WeatherItem(

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("main")
	val main: String,

	@field:SerializedName("id")
	val id: Int
)

data class FeelsLike(

	@field:SerializedName("eve")
	val eve: Number,

	@field:SerializedName("night")
	val night: Number,

	@field:SerializedName("day")
	val day: Number,

	@field:SerializedName("morn")
	val morn: Number
)

object CurrentWeatherKeys {

	val dt = intPreferencesKey("dt_key")
	val sunrise = intPreferencesKey("sunrise_key")
	val sunset = intPreferencesKey("sunset_key")
	val temp = intPreferencesKey("temp_key")
	val feelsLike = intPreferencesKey("feelsLike_key")
	val pressure = intPreferencesKey("pressure_key")
	val humidity = intPreferencesKey("humidity_key")
	val clouds = intPreferencesKey("clouds_key")
	val uvi = intPreferencesKey("uvi_key")
	val visibility = intPreferencesKey("visibility_key")
	val wind_speed = intPreferencesKey("windSpeed_key")
	val windDeg = intPreferencesKey("windDegree_key")

}