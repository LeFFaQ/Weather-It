package com.lffq.weatherapp.network

import com.lffq.weatherapp.network.models.current.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Мост к OpenWeatherMap.
 * Здесь описаны ключевые функции,
 * при вызове которых, мы будем обращатся к OWM
 */
interface WeatherApi {

    /**
     * Получение текущей погоды по географич. координатам
     * @param lat Широта
     * @param lon Долгота
     * @param appid API-ключ
     * @param unit Единица измерения
     * @return Результат запроса
     */
    @GET("/data/2.5/weather")
    suspend fun getWeatherByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") unit: String = "metric"
    ): Response<WeatherModel>

    /**
     * Получение текущей погоды по городу
     * @param city Необходимый город
     * @param appid API-ключ
     * @param unit Единица измерения
     * @return Результат запроса
     */
    @GET("/data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") unit: String = "metric"
    ): Response<WeatherModel>

}



