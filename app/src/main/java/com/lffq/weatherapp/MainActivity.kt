package com.lffq.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lffq.weatherapp.network.WeatherApi
import com.lffq.weatherapp.ui.theme.WeatherTutorialTheme

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
            }
        }
    }
}