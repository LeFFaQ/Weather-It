/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lffq.weatherapp._dayIcons
import com.lffq.weatherapp._nightIcons
import com.lffq.weatherapp.data.DataStoreRepository
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.models.base.current.WeatherModel
import com.lffq.weatherapp.network.models.base.onecall.OneCallModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: WeatherRepository,
    private val DSRepo: DataStoreRepository,
) : ViewModel() {

    private val _city = MutableLiveData<String?>(null)
    private val _lat = MutableLiveData<Double?>(null)
    private val _lon = MutableLiveData<Double?>(null)

    private val _weather = MutableLiveData<WeatherModel>()
    val weather: LiveData<WeatherModel> = _weather

    private val _forecast = MutableLiveData<OneCallModel>()
    val forecast: LiveData<OneCallModel> = _forecast

    private val _dataLoaded = MutableLiveData<Boolean>()
    val dataLoaded: LiveData<Boolean> = _dataLoaded

    private val _currentIcon = MutableLiveData<Int>()
    val currentIcon: LiveData<Int> = _currentIcon

    var searchCity by mutableStateOf("")

    // Функция, которая будет
    // Вызыватся при создании класса
    init {
        //getCityFromDS()
        forecastFromOneCall(55.3333, 86.0833)
    }

    private fun getCityFromDS() {
        viewModelScope.launch {
            DSRepo.readCityValues().collect {
                _city.value = checkNotNull(it.city)
                _lat.value = checkNotNull(it.lat)
                _lon.value = checkNotNull(it.lon)
            }
        }
    }

    private fun getSavedWeather() {

    }

    fun chooseWeatherStatusIcon(icon: String) {

        if ("n" in icon) {
            _currentIcon.value = _nightIcons[icon]
            return
        }
        _currentIcon.value = _dayIcons[icon]

    }

    fun getCurrentWeatherByCity(searchCity: String) {
        viewModelScope.launch {
            val result = repo.getWeatherByCity(searchCity)

            if (result.isSuccessful) {
                result.body()?.let {
                    _weather.value = it
                    chooseWeatherStatusIcon(icon = it.weather[0].icon)
                }
            }

        }
    }

    fun onSearchCityChanged(new: String) {
        searchCity = new
    }

    fun forecastFromOneCall(lat: Double, lon: Double) {
        viewModelScope.launch {
            val result = repo.getDailyForecast(lat, lon)
            if (result.isSuccessful) {
                result.body()?.let {
                    _forecast.value = it
                }
            }
        }
    }
}

