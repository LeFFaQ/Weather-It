/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.network.models.base.current

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