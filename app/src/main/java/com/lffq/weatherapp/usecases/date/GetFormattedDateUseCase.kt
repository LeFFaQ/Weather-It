/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.usecases.date

import android.os.Build
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toJavaZoneId
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class GetFormattedDateUseCase(private val timeZone: TimeZone, private val locale: Locale) {

    fun execute(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val now = Clock.System.now()
            val formatter = DateTimeFormatter.ofPattern("E, MMM d, yyyy")
            return formatter.format(now.toJavaInstant().atZone(timeZone.toJavaZoneId()))
        }

        val pattern = "E, MMM d, yyyy"

        val simpleDateFormat = SimpleDateFormat(pattern, locale)

        return simpleDateFormat.format(Date())
    }

}