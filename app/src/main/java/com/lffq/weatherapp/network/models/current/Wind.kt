package com.lffq.weatherapp.network.models.current

import com.google.gson.annotations.SerializedName

data class Wind(

    @field:SerializedName("deg")
    val deg: Number,

    @field:SerializedName("speed")
    val speed: Number
)