package com.lffq.weatherapp.network

import com.lffq.weatherapp.BuildConfig
import com.lffq.weatherapp.network.models.current.WeatherModel
import retrofit2.Response


class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {

    // Класс, который будет
    // Возвращать реализацию
    // интерфейса WeatherRepository

    override suspend fun getWeatherByCoord(
        lat: Double,
        lon: Double,
        unit: String
    ): Response<WeatherModel> {
        return api.getWeatherByCoord(lat, lon, BuildConfig.API_KEY)
    }

    override suspend fun getWeatherByCity(
        city: String,
        unit: String
    ): Response<WeatherModel> {
        return api.getWeatherByCity(city, BuildConfig.API_KEY, unit)
    }

}


