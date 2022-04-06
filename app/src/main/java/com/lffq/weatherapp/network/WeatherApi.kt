package com.lffq.weatherapp.network

import com.lffq.weatherapp.network.models.current.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    // Это наш мост к OpenWeatherMap.
    // Здесь мы будем описывать функции,
    // которые будут получать данные о погоде

    // Получаем текущую погоду по географич. координатам
    @GET("/weather")
    suspend fun getWeatherByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") unit: String = "metric"
    ): Response<WeatherModel>

    // Получаем текущую погоду по городу
    @GET("/weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") unit: String = "metric"
    ): Response<WeatherModel>

}



