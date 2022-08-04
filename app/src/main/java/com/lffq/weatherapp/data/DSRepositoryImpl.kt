/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.lffq.weatherapp.network.models.base.onecall.Current
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class DSRepositoryImpl(context: Context) : DataStoreRepository {

    private val dataStore = context.dataStore

//    override suspend fun saveValues(model: StoreValues) {
//        dataStore.edit { preferences ->
//            preferences[PreferencesKey.checked] = model.checked
//            preferences[PreferencesKey.lat] = model.lat!!
//            preferences[PreferencesKey.lon] = model.lon!!
//            preferences[PreferencesKey.city] = model.city!!
//            preferences[PreferencesKey.temp] = model.temp!!
//            preferences[PreferencesKey.date] = model.date!!
//        }
//    }
//
//    override fun readValues(): Flow<StoreValues> {
//        return dataStore.data
//            .catch { exception ->
//                if (exception is IOException) {
//                    emit(emptyPreferences())
//                } else {
//                    throw exception
//                }
//            }
//            .map {
//                StoreValues(
//                    it[PreferencesKey.checked]!!,
//                    it[PreferencesKey.lat],
//                    it[PreferencesKey.lon],
//                    it[PreferencesKey.city],
//                    it[PreferencesKey.temp],
//                    it[PreferencesKey.date],
//                )
//            }
//    }

    override suspend fun saveSkipState() {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.skipState] = true
        }
    }

    override fun readSkipState(): Flow<Boolean> {
        return dataStore.data
            .onCatch()
            .map { preferences ->
                preferences[PreferencesKey.skipState] ?: false
            }
    }

    override suspend fun saveCityValues(values: CityValues?) {
        dataStore.edit { preferences ->
            values?.let { values ->
                preferences[PreferencesKey.city] = values.city!!
                preferences[PreferencesKey.lon] = values.lon!!
                preferences[PreferencesKey.lat] = values.lat!!
            }
        }
    }

    override fun readCityValues(): Flow<CityValues> {

        return dataStore.data
            .onCatch()
            .map { preferences ->
                val city = preferences[PreferencesKey.city]
                val lon = preferences[PreferencesKey.lon]
                val lat = preferences[PreferencesKey.lat]

                CityValues(
                    city = checkNotNull(city),
                    lon = checkNotNull(lon),
                    lat = checkNotNull(lat)
                )
            }
    }

    override suspend fun saveClimaticValues(values: Current?) {
        dataStore.edit { preferences ->
            values.let { values ->
                //preferences[PreferencesKey.temp] = values.temp
                //preferences[PreferencesKey.date] = values.date!!
            }
        }
    }

    override fun readClimaticValues(): Flow<Current> {
//        return dataStore.data
//            .onCatch()
//            .map { preferences ->
//                val temp = preferences[PreferencesKey.temp]
//                val date = preferences[PreferencesKey.date]
//
//                Current()
//            }
        TODO()
    }

    private fun Flow<Preferences>.onCatch() = catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }

    private object PreferencesKey {
        val skipState = booleanPreferencesKey(name = "skip_state")

        val lat = doublePreferencesKey(name = "lat_pref")
        val lon = doublePreferencesKey(name = "lon_pref")
        val city = stringPreferencesKey(name = "city_pref")

        val temp = intPreferencesKey(name = "temp_pref")
        val date = stringPreferencesKey(name = "date_pref")

    }
}