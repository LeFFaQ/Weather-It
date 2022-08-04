/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.usecases.other

import com.lffq.weatherapp._dayIcons
import com.lffq.weatherapp._nightIcons

class GetIconFromIdUseCase {

    fun execute(icon: String): Int {
        return if ("n" in icon) {
            _nightIcons[icon]!!
        } else {
            _dayIcons[icon]!!
        }
    }

}