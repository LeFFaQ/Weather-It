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


import com.lffq.weatherapp.viewmodel.MainViewModel
import com.lffq.weatherapp.viewmodel.WelcomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Модуль, который будет создавать,
    // viewModel'и

    viewModel {
        MainViewModel(repo = get(), DSRepo = get())
    }

    viewModel {
        WelcomeViewModel(repo = get(), DSRepo = get(), androidApplication())
    }
}