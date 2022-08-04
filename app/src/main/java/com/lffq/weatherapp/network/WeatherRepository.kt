/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.network

import com.lffq.weatherapp.network.models.base.current.WeatherModel
import com.lffq.weatherapp.network.models.base.geocoding.GeocodingModelItem
import com.lffq.weatherapp.network.models.base.onecall.OneCallModel
import retrofit2.Response

interface WeatherRepository {

    // Это часть паттерна "Репозиторий".
    // Здесь мы указываем как
    // нам обращатся к WeatherApi

    suspend fun getWeatherByCoord(
        lat: Double,
        lon: Double,
        unit: String = "metric"
    ): Response<WeatherModel>

    suspend fun getWeatherByCity(
        city: String,
        unit: String = "metric"
    ): Response<WeatherModel>

    suspend fun getDailyForecast(
        lat: Double,
        lon: Double,
    ): Response<OneCallModel>


    suspend fun getGeocoding(
        city: String,
    ): Response<List<GeocodingModelItem?>?>

    suspend fun getCityFromLatLng(
        lat: Double,
        lon: Double,
    ): Response<List<GeocodingModelItem>>
}



