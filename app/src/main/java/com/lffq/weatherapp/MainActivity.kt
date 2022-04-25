package com.lffq.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.lffq.weatherapp.ui.navigation.Screen
import com.lffq.weatherapp.ui.navigation.SetupNavGraph
import com.lffq.weatherapp.ui.theme.WeatherTutorialTheme
import com.lffq.weatherapp.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val vm: WelcomeViewModel by viewModel()

    // Точка входа входа в приложение
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getSkipState()
        installSplashScreen()

        // Эта функция будет рисовать наш интерфейс
        setContent {

            var start = Screen.Welcome.route
            if (vm.skipState.value!!) {
                start = Screen.Main.route
            }

            val navController = rememberNavController()

            // Тема Нашего приложения
            // Находится в файле Theme.kt нашего приложения
            // Это Composable-функция, в которой
            // Указываются цвета светлой/темной тем,
            // Цвет заднего фона и определенный компонентов.
            WeatherTutorialTheme {

                SetupNavGraph(
                    navHostController = navController,
                    startDestination =  start
                )

            }
        }
    }
}