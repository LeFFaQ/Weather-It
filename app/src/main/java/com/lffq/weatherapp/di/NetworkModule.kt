package com.lffq.weatherapp.di

import com.lffq.weatherapp.network.WeatherApi
import com.lffq.weatherapp.network.WeatherBuilder
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.WeatherRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    // Модуль, предоставляющий доступ
    // к всем необходимым объектам
    // в любой момент времени

    single { WeatherBuilder.InterceptorInstance() }

    single { WeatherBuilder.OkHttpInstance(get()) }

    single { WeatherBuilder.RetrofitInstance(get(), "https://api.openweathermap.org") }

    single { get<Retrofit>().create(WeatherApi::class.java) }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}
