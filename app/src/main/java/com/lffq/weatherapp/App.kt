/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp

import android.app.Application
import com.lffq.weatherapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App: Application() {

    override fun onCreate() {
        super.onCreate()

        // Входная точка для DI.
        // Функция считывает все модули
        // и запускает по мере необходимости
        // (функции get() inject())
        startKoin {
            androidContext(this@App)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(listOf(appModule, networkModule, dataModule, dateModule, otherModule))
        }


    }
}