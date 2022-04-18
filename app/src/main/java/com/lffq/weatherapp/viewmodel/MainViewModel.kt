package com.lffq.weatherapp.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.lffq.weatherapp.appid
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.models.current.WeatherModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: WeatherRepository,
    private val application: Application
): ViewModel() {

    private val _weather = MutableLiveData<WeatherModel>()
    val weather: LiveData<WeatherModel> = _weather


    private val _dataLoaded = MutableLiveData<Boolean>()
    private val _imageLoaded = MutableLiveData<Boolean>()
    val dataLoaded: LiveData<Boolean> = _dataLoaded
    val imageLoaded: LiveData<Boolean> = _imageLoaded

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> = _bitmap

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
                    result.body()?.let {
                        _weather.value = it
                        _dataLoaded.value = true
                        getBitmap(it.weather[0].icon)
                    }
            }
        }
    }

    private fun getBitmap(icon: String) {

        viewModelScope.launch {
            val loader = ImageLoader(application.baseContext)
            val request = ImageRequest.Builder(application.baseContext)
                .data("https://openweathermap.org/img/wn/${icon}@2x.png")
                .allowHardware(false)
                .build()

            
            val result = (loader.execute(request) as SuccessResult).drawable
            _bitmap.value = (result as BitmapDrawable).bitmap
            _imageLoaded.value = true


        }

    }
}


//initialize it in koin module
//in composable:
//val vm = getViewModel<MainViewModel>()

//access to livedata's
//val example: String by vm.exampleVar.observeAsState("")


