/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp


val _dayIcons = mapOf(
    "01d" to R.raw.clear_day,
    "02d" to R.raw.overcast_day,
    "03d" to R.raw.overcast_day,
    "04d" to R.raw.extreme_day,
    "09d" to R.raw.overcast_day_drizzle,
    "10d" to R.raw.overcast_day_rain,
    "11d" to R.raw.thunderstorms_day_overcast,
    "13d" to R.raw.overcast_day_snow,
    "50d" to R.raw.overcast_day_haze,
)

val _nightIcons = mapOf(
    "01n" to R.raw.clear_night,
    "02n" to R.raw.overcast_night,
    "03n" to R.raw.overcast_night,
    "04n" to R.raw.extreme_night,
    "09n" to R.raw.overcast_night_drizzle,
    "10n" to R.raw.overcast_night_rain,
    "11n" to R.raw.thunderstorms_night_overcast,
    "13n" to R.raw.overcast_night_snow,
    "50n" to R.raw.overcast_night_haze,
)

val appPermissions = listOf(
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.ACCESS_COARSE_LOCATION
)






































