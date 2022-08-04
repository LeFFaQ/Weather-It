/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColors(
    primary = Color(0xffa8c8ff),
    primaryVariant = Color(0xff00458e),
    secondary = Color(0xff9fcaff),
    secondaryVariant = Color(0xFF004880),
    background = Color(0xff1C1B1F),
    surface = Color(0xff1C1B1F),
    error = Color(0xffF2B8B5),
    onPrimary = Color(0xff002f65),
    onSecondary = Color(0xff00325b),
    onBackground = Color(0xffE6E1E5),
    onSurface = Color(0xffE6E1E5),
    onError = Color(0xff601410)
)

val LightColorPalette = lightColors(
    primary = Color(0xff005cb9),
    primaryVariant = Color(0xff001b3f),
    secondary = Color(0xff0a61a4),
    secondaryVariant = Color(0xff001c38),
    background = Color(0xffFFFBFE),
    surface = Color(0xffFFFBFE),
    error = Color(0xffB3261E),
    onPrimary = Color(0xffffffff),
    onSecondary = Color(0xffffffff),
    onBackground = Color(0xff1C1B1F),
    onSurface = Color(0xff1C1B1F),
    onError = Color(0xffFFFFFF)
)