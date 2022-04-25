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

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveSkipState(state: Boolean)

    fun readSkipState(): Flow<Boolean>

    suspend fun saveCityValues(values: CityValues)

    fun readCityValues(): Flow<CityValues>

    suspend fun saveClimaticValues(values: ClimaticValues)

    fun readClimaticValues(): Flow<ClimaticValues>

}