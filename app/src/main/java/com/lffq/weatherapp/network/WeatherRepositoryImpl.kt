package com.lffq.weatherapp.network

import com.lffq.weatherapp.network.models.current.WeatherModel
import retrofit2.Response


class WeatherRepositoryImpl(private val api: WeatherApi): WeatherRepository {

    // Класс, который будет
    // Возвращать реализацию
    // интерфейса WeatherRepository

    override suspend fun getWeatherByCoord(
        lat: Double,
        lon: Double,
        appid: String,
        unit: String
    ): Response<WeatherModel> {
        return api.getWeatherByCoord(lat, lon, appid)
    }

    override suspend fun getWeatherByCity(
        city: String,
        appid: String,
        unit: String
    ): Response<WeatherModel> {
        return api.getWeatherByCity(city, appid, unit)
    }

}


