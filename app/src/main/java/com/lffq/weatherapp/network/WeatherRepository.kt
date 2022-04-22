package com.lffq.weatherapp.network

import com.lffq.weatherapp.network.models.current.WeatherModel
import retrofit2.Response
import retrofit2.http.GET

interface WeatherRepository {

    // Это часть паттерна "Репозиторий".
    // Здесь мы указываем как
    // нам обращатся к WeatherApi

    suspend fun getWeatherByCoord(
        lat: Double,
        lon: Double,
        unit: String = "metric"
    ): Response<WeatherModel>

    @GET("/weather")
    suspend fun getWeatherByCity(
        city: String,
        unit: String = "metric"
    ): Response<WeatherModel>
}



