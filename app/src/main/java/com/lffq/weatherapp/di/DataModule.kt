/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.di

import com.lffq.weatherapp.local.DSRepositoryImpl
import com.lffq.weatherapp.local.DataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {
    single<DataStoreRepository> { DSRepositoryImpl(androidContext()) }
}