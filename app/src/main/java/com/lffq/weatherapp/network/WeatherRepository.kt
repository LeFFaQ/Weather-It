package com.lffq.weatherapp.network

import com.lffq.weatherapp.network.models.current.WeatherModel
import com.lffq.weatherapp.network.models.geocoding.GeocodingModelItem
import com.lffq.weatherapp.network.models.onecall.OneCallModel
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
}



