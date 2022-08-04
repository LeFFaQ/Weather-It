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

import com.lffq.weatherapp.usecases.date.GetCurrentTimeZoneUseCase
import com.lffq.weatherapp.usecases.date.GetFormattedDateUseCase
import com.lffq.weatherapp.usecases.date.GetLocaleUseCase
import com.lffq.weatherapp.usecases.date.GetTimeFromUnixUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dateModule = module {

    factory { GetLocaleUseCase(androidContext())  }

    factory { GetCurrentTimeZoneUseCase()}

    factory { GetFormattedDateUseCase(get<GetCurrentTimeZoneUseCase>().execute(), get<GetLocaleUseCase>().execute()!!) }

    factory { GetTimeFromUnixUseCase(get<GetCurrentTimeZoneUseCase>().execute()) }

}