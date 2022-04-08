package com.lffq.weatherapp.di


import com.lffq.weatherapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Модуль, который будет создавать,
    // viewModel'и

    viewModel {
        MainViewModel(get())
    }
}

