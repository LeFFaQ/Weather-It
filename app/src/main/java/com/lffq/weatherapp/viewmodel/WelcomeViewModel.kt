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

import android.app.Application
import android.content.ContentValues.TAG
import android.location.Location
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.birjuvachhani.locus.Locus
import com.google.android.gms.maps.model.LatLng
import com.lffq.weatherapp.data.DataStoreRepository
import com.lffq.weatherapp.network.WeatherRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val repo: WeatherRepository,
    private val DSRepo: DataStoreRepository,
    private val app: Application
) : ViewModel() {


    var city by mutableStateOf("")

//
//    private val _cities: MutableLiveData<List<GeocodingModelItem?>?> = MutableLiveData()
//    val cities: LiveData<List<GeocodingModelItem?>?> = _cities
//
//    private val _selectedCity: MutableLiveData<GeocodingModelItem> = MutableLiveData()
//    //val selectedCity: LiveData<GeocodingModelItem> = _selectedCity
//
//    private val _skipState: MutableLiveData<Boolean> = MutableLiveData(false)
//    val skipState: LiveData<Boolean> = _skipState
//
//    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
//    val isLoading: State<Boolean> = _isLoading
//
//    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Welcome.route)
//    val startDestination: State<String> = _startDestination
//
//    private val _location: MutableState<Location?> = mutableStateOf(null)
//    val location: State<Location?> = _location

    private val _uiState = mutableStateOf<WelcomeViewState>(WelcomeViewState.LoadingState)
    val uiState: State<WelcomeViewState>
        get() = _uiState

    private val _location: MutableState<Location?> = mutableStateOf(null)
    private val _cityName: MutableState<String?> = mutableStateOf(null)
    private val _region: MutableState<String?> = mutableStateOf(null)

    init {
        Log.d(TAG, "init function in WelcomeVM ")
        getLocation()
        //chooseDestination()
    }

//    private fun chooseDestination() {
//        viewModelScope.launch {
//            DSRepo.readSkipState().collect { state ->
//                if (state) {
//                    _startDestination.value = Screen.Main.route
//                }
//            }
//            _isLoading.value = false
//        }
//    }
//
//    var queryJob: Job? = null
//    fun cityFromGeocodingApi() {
//        queryJob?.cancel()
//        queryJob = viewModelScope.launch {
//            delay(1000)
//            val result = repo.getGeocoding(city)
//            if (result.isSuccessful) {
//                _cities.value = result.body()
//            }
//        }
//
//    }
//
//    fun onQueryCity(query: String) {
//        city = query
//        if (query.length >= 2) {
//            cityFromGeocodingApi()
//        }
//
//    }
//
//    fun setSelectedCity(value: GeocodingModelItem) {
//        Log.d(TAG, "saveCityToDS: $_selectedCity")
//        _selectedCity.value = value
//    }
//
//    fun saveCityToDS() {
//        viewModelScope.launch {
//            Log.d(TAG, "saveCityToDS: $_selectedCity")
//            DSRepo.saveCityValues(
//                CityValues(
//                    city = _selectedCity.value!!.name,
//                    lon = _selectedCity.value!!.lon,
//                    lat = _selectedCity.value!!.lat,
//                )
//            )
//            DSRepo.saveSkipState()
//        }
//    }
//
//    fun getSkipState() {
//        viewModelScope.launch {
//            DSRepo.readSkipState().collect {
//                Log.d(TAG, "getSkipState: $it")
//                _skipState.value = it
//            }
//        }
//    }

    private fun getLocation() {
        _uiState.value = WelcomeViewState.LoadingState

        Locus.getCurrentLocation(app.applicationContext) { result ->
            result.location?.let { _location.value = it }
            result.error?.let { Log.d(TAG, "getLocation: $it") }
        }
    }

    private fun getCityFromLatLng() {
        viewModelScope.launch {
            _location.value?.let {
                val result = repo.getCityFromLatLng(it.latitude, it.longitude)
                if (result.isSuccessful) {
                    _uiState.value = WelcomeViewState.LoadedState(LatLng(it.latitude, it.longitude), result.body()[0].name)
                }
            }
        }
    }

}

sealed class WelcomeViewState {
    data class LoadedState(val latLng: LatLng, val cityName: String, val Region: String) : WelcomeViewState()

    object LoadingState : WelcomeViewState()

    data class ErrorState(val e: Throwable) : WelcomeViewState()
}