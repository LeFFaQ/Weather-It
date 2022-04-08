package com.lffq.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lffq.weatherapp.ui.theme.WeatherTutorialTheme
import com.lffq.weatherapp.view.MainView

class MainActivity : ComponentActivity() {

    // Точка входа входа в приложение
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Эта функция будет рисовать наш интерфейс
        setContent {

            // Тема Нашего приложения
            // Находится в файле Theme.kt нашего приложения
            // Это Composable-функция, в которой
            // Указываются цвета светлой/темной тем,
            // Цвет заднего фона и определенный компонентов.
            WeatherTutorialTheme {

                MainView()

            }
        }
    }
}