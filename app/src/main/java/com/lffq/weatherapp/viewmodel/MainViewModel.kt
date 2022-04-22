package com.lffq.weatherapp.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lffq.weatherapp._dayIcons
import com.lffq.weatherapp._nightIcons
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.models.current.WeatherModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toJavaZoneId
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class MainViewModel(
    private val repo: WeatherRepository,
    private val application: Application
) : ViewModel() {

    private val _weather = MutableLiveData<WeatherModel>()
    val weather: LiveData<WeatherModel> = _weather

    private val _dataLoaded = MutableLiveData<Boolean>()
    val dataLoaded: LiveData<Boolean> = _dataLoaded

    private val _currentIcon = MutableLiveData<Int>()
    val currentIcon: LiveData<Int> = _currentIcon

    var searchCity by mutableStateOf("")

    // Функция, которая будет
    // Вызыватся при создании класса
    init {
        getCurrentWeatherByCity("Kemerovo")
    }

    // Получение текущей погоды
    private fun getCurrentWeather() {
        // Вызов Suspend-функций
        viewModelScope.launch {
            // Получаем текущую погоду
            val result = repo.getWeatherByCity("Kemerovo")
            // Проверяем на успешность
            if (result.isSuccessful) {
                // Записываем
                result.body()?.let {
                    _weather.value = it
                    _dataLoaded.value = true
                }
            }
        }
    }

    fun getFormattedDate(): String {
        val systemTZ = TimeZone.currentSystemDefault()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val now = Clock.System.now()

            val formatter = DateTimeFormatter.ofPattern("E, MMM d, yyyy")
            return formatter.format(now.toJavaInstant().atZone(systemTZ.toJavaZoneId()))
        }
        val pattern = "E, MMM d, yyyy"

        val locale = getCurrentLocale(context = application.applicationContext)
        val simpleDateFormat = SimpleDateFormat(pattern, locale)

        return simpleDateFormat.format(Date())
    }

    fun getCurrentLocale(context: Context): Locale? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0)
        } else {
            context.resources.configuration.locale
        }
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
}


//initialize it in koin module
//in composable:
//val vm = getViewModel<MainViewModel>()

//access to livedata's
//val example: String by vm.exampleVar.observeAsState("")


