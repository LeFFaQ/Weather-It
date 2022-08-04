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

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Контейнер
 * с функциями для DI
 */
object WeatherBuilder {

    /**
     * Создание клиента Retrofit
     * @param client [OkHttpClient]
     * @param url Базовый URL
     * @return экземпляр [Retrofit]
     */
    fun RetrofitInstance(client: OkHttpClient, url: String): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
    }

    /**
     * Создание клиента OkHttp
     * @param loggingInterceptor [HttpLoggingInterceptor]
     * @return экземпляр [OkHttpClient]
     */
    fun OkHttpInstance(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Создание перехватчика запросов
     * @return экземпляр [HttpLoggingInterceptor]
     */
    fun InterceptorInstance(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            // Уровень перехватывания
            .apply { this.level = HttpLoggingInterceptor.Level.BODY }
    }

}


