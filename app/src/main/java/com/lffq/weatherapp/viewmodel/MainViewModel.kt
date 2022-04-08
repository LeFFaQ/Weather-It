package com.lffq.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lffq.weatherapp.appid
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.models.current.WeatherModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: WeatherRepository
): ViewModel() {

    private val _weather = MutableLiveData<WeatherModel>()
    val weather = _weather

    // Функция, которая будет
    // Вызыватся при создании класса
    init {
        getCurrentWeather()
    }

    // Получение текущей погоды
    private fun getCurrentWeather() {
        // Вызов Suspend-функций
        viewModelScope.launch {
            // Получаем текущую погоду
            val result = repo.getWeatherByCity("Kemerovo", appid)
            // Проверяем на успешность
            if (result.isSuccessful) {
                // Записываем
                _weather.value = result.body()
            }
        }
    }
}


