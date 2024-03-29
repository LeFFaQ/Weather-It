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
     * @param unit Единица измерения (default: "metric")
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
     * @param unit Единица измерения (default: "metric")
     * @return Результат запроса
     */
    @GET("/data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") unit: String = "metric"
    ): Response<WeatherModel>


    /**
     * Получение прогноза погоды (One Call API)
     * (только по географическим координатам)
     * @param lat Широта
     * @param lon Долгота
     * @param appid API-ключ
     * @param exclude Убрать лишние параметры (default: "current,minutely,hourly,alerts")
     * @param unit Единица измерения (default: "metric")
     * @return Результат запроса
     */
    @GET("/data/2.5/onecall")
    suspend fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("exclude") exclude: String = "minutely,hourly,alerts",
        @Query("units") unit: String = "metric"
    ): Response<OneCallModel>


    /**
     * Геокодинг города.
     * Получение координат города и названий на разных языках.
     * @param q Город
     * @param appid API-ключ
     * @return Список городов-совпадений с [q]
     */
    @GET("/geo/1.0/direct")
    suspend fun getGeocoding(
        @Query("q") city: String,
        @Query("appid") appid: String,
    ): Response<List<GeocodingModelItem?>?>

    @GET("/geo/1.0/reverse")
    suspend fun getCityFromLatLng(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
    ): Response<List<GeocodingModelItem>>

}



