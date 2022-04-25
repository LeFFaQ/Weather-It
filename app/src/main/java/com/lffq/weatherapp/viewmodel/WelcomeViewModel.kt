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

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lffq.weatherapp.local.CityValues
import com.lffq.weatherapp.local.DataStoreRepository
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.models.geocoding.GeocodingModelItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val repo: WeatherRepository,
    private val DSRepo: DataStoreRepository
) : ViewModel() {


    var city by mutableStateOf("")


    private val _cities: MutableLiveData<List<GeocodingModelItem?>?> = MutableLiveData()
    val cities: LiveData<List<GeocodingModelItem?>?> = _cities

    private val _selectedCity: MutableLiveData<GeocodingModelItem> = MutableLiveData()
    //val selectedCity: LiveData<GeocodingModelItem> = _selectedCity


    private val _skipState: MutableLiveData<Boolean> = MutableLiveData(false)
    val skipState: LiveData<Boolean> = _skipState


    init {
        Log.d(TAG, "init function in WelcomeVM ")
    }

    fun cityFromGeocodingApi(query: String) {

        if (query.length >= 2) {
            viewModelScope.launch {
                delay(1000)
                val result = repo.getGeocoding(query)
                if (result.isSuccessful) {
                    _cities.value = result.body()
                }
            }
        } else {
            if (_cities.value!!.isNotEmpty()) {
                _cities.value = null
            }
        }

    }

    fun onQueryCity(query: String) {
        city = query

        if (query.length >= 2) {
            cityFromGeocodingApi(query)
        }
    }

    fun setSelectedCity(value: GeocodingModelItem) {
        Log.d(TAG, "saveCityToDS: $_selectedCity")
        _selectedCity.value = value
    }

    fun saveCityToDS() {
        viewModelScope.launch {
            Log.d(TAG, "saveCityToDS: $_selectedCity")
            DSRepo.saveCityValues(CityValues(
                city = _selectedCity.value!!.name,
                lon = _selectedCity.value!!.lon,
                lat = _selectedCity.value!!.lat,
            ))
            DSRepo.saveSkipState(true)
        }
    }
    fun getSkipState() {
        viewModelScope.launch {
            DSRepo.readSkipState().collect {
                Log.d(TAG, "getSkipState: $it")
                _skipState.value = it
            }
        }
    }
}