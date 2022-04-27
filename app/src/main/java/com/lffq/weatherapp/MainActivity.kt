package com.lffq.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

            val route by remember {
                mutableStateOf(value = if (vm.skipState.value!!) Screen.Main.route else Screen.Welcome.route )
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
                    startDestination =  route
                )

            }
        }
    }
}