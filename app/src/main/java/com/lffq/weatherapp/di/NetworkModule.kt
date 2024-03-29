/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.di

import com.lffq.weatherapp.network.WeatherApi
import com.lffq.weatherapp.network.WeatherBuilder
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.WeatherRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Модуль для Api, Retrofit и т.д.
 *
 */
val networkModule = module {

    single { WeatherBuilder.InterceptorInstance() }

    single { WeatherBuilder.OkHttpInstance(get()) }

    single { WeatherBuilder.RetrofitInstance(get(), "https://api.openweathermap.org") }

    single { get<Retrofit>().create(WeatherApi::class.java) }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}
