/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lffq.weatherapp.view.MainView
import com.lffq.weatherapp.view.WelcomeView


@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    startDestination: String
) {
    //optional UI
    //val systemUiController = rememberSystemUiController()
    //val useDarkIcons = MaterialTheme.colors.isLight

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Main.route) {
            MainView()
        }

        composable(route = Screen.Welcome.route) {
            WelcomeView(navHostController)
        }
    }
}