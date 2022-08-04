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