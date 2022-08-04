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
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaZoneId
import java.time.Instant
import java.time.format.DateTimeFormatter

class GetTimeFromUnixUseCase(val timeZone: TimeZone) {

    fun execute(unixTime: Long): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val unix: Instant = Instant.ofEpochSecond(unixTime)

            val formatter = DateTimeFormatter.ofPattern("E d")
            return formatter.format(unix.atZone(timeZone.toJavaZoneId()))
        }
        // todo: Доделать
        return "Some date at Build.VERSION_CODES.O"
    }
}