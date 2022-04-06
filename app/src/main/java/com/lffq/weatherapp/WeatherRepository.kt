package com.lffq.weatherapp

import com.lffq.weatherapp.network.models.current.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRepository {

    // Это часть паттерна "Репозиторий".
    // Здесь мы указываем как
    // нам обращатся к WeatherApi

    suspend fun getWeatherByCoord(
        lat: Double,
        lon: Double,
        appid: String,
        unit: String = "metric"
    ): Response<WeatherModel>

    @GET("/weather")
    suspend fun getWeatherByCity(
        city: String,
        appid: String,
        unit: String = "metric"
    ): Response<WeatherModel>
}



