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
        MainViewModel(repo = get(), DSRepo = get(), androidApplication())
    }

    viewModel {
        WelcomeViewModel(repo = get(), DSRepo = get())
    }
}

