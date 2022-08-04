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