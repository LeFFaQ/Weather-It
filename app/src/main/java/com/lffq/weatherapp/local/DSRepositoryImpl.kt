/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.local

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
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

    override suspend fun saveSkipState(state: Boolean) {
        dataStore.edit { preferences ->
            Log.d(TAG, "saveSkipState: ${state}")
            preferences[PreferencesKey.skipState] = state
        }
    }

    override fun readSkipState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                Log.d(TAG, "readSkipState: ${it[PreferencesKey.skipState]}")
                it[PreferencesKey.skipState] ?: false }
    }

    override suspend fun saveCityValues(values: CityValues) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.city] = values.city!!
            preferences[PreferencesKey.lat] = values.lat!!
            preferences[PreferencesKey.lon] = values.lon!!
        }
    }

    override fun readCityValues(): Flow<CityValues> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                CityValues(
                    it[PreferencesKey.city],
                    it[PreferencesKey.lon],
                    it[PreferencesKey.lat]
                )
            }
    }

    override suspend fun saveClimaticValues(values: ClimaticValues) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.temp] = values.temp!!
            preferences[PreferencesKey.date] = values.date!!
        }
    }

    override fun readClimaticValues(): Flow<ClimaticValues> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {
                ClimaticValues(
                    it[PreferencesKey.temp],
                    it[PreferencesKey.date],
                )
            }
    }

    private object PreferencesKey {
        val skipState = booleanPreferencesKey("skip_state")

        val lat = doublePreferencesKey(name = "lat_pref")
        val lon = doublePreferencesKey(name = "lon_pref")
        val city = stringPreferencesKey(name = "city_pref")

        val temp = intPreferencesKey(name = "temp_pref")
        val date = stringPreferencesKey(name = "date_pref")

    }
}