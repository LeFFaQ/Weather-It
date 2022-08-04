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

import com.lffq.weatherapp.network.models.base.onecall.Current
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveSkipState()

    fun readSkipState(): Flow<Boolean>

    suspend fun saveCityValues(values: CityValues?)

    fun readCityValues(): Flow<CityValues>

    suspend fun saveClimaticValues(values: Current?)

    fun readClimaticValues(): Flow<Current>
}