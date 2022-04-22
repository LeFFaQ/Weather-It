package com.lffq.weatherapp.network.models.current

import com.google.gson.annotations.SerializedName


data class WeatherModel(

    @field:SerializedName("visibility")
    val visibility: Int,

    @field:SerializedName("timezone")
    val timezone: Int,

    @field:SerializedName("main")
    val main: Main,

    @field:SerializedName("clouds")
    val clouds: Clouds,

    @field:SerializedName("sys")
    val sys: Sys,

    @field:SerializedName("dt")
    val dt: Int,

    @field:SerializedName("coord")
    val coord: Coord,

    @field:SerializedName("weather")
    val weather: List<WeatherItem>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("cod")
    val cod: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("base")
    val base: String,

    @field:SerializedName("wind")
    val wind: Wind
)