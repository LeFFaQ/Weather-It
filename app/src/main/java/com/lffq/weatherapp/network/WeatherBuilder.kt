package com.lffq.weatherapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherBuilder {

    // В этих трех функциях,
    // мы будем создавать
    // Экземпляры классов
    // - Retrofit
    // - OkHttpClient
    // - HttpLoggingInterceptor
    //
    // Это нам понадобится позднее.

    fun RetrofitInstance(client: OkHttpClient, url: String): Retrofit {
        // Создаем экземпляр Retrofit
        // Передаем наш OkHttpClient
        // и Базовый URL
        // (укажем позднее)
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
    }

    fun OkHttpInstance(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        // Создаем экземпляр OkHttpClient
        // передаем HttpLoggingInterceptor
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun InterceptorInstance(): HttpLoggingInterceptor {
        // Создаем экземпляр HttpLoggingInterceptor
        // и указываем уровень оповещения
        return HttpLoggingInterceptor()
            .apply { this.level = HttpLoggingInterceptor.Level.BODY }
    }

}




