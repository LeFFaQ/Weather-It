/*
 *  __         ______     ______   ______   ______     ______
 * /\ \       /\  ___\   /\  ___\ /\  ___\ /\  __ \   /\  __ \
 * \ \ \____  \ \  __\   \ \  __\ \ \  __\ \ \  __ \  \ \ \/\_\
 *  \ \_____\  \ \_____\  \ \_\    \ \_\    \ \_\ \_\  \ \___\_\
 *   \/_____/   \/_____/   \/_/     \/_/     \/_/\/_/   \/___/_/
 * Created by LeFFaQ
 * Copyright (c) 2022 . All rights reserved.
 */

package com.lffq.weatherapp.view

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.lffq.weatherapp.R
import com.lffq.weatherapp.viewmodel.WelcomeViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun WelcomeView(navController: NavController) {

    val vm = getViewModel<WelcomeViewModel>()
    val location by vm.location


    Background {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "STARTUP TEXT")
            MapView(location)
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun MapView(location: Location?) {
    val localconfig = LocalConfiguration.current

    val isLoading = location == null
    val cameraState = rememberCameraPositionState()

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
        elevation = 24.dp,
        modifier = Modifier
    ) {
        Box {
            Column {
                Box(contentAlignment = Alignment.Center) {
                    GoogleMap(
                        cameraPositionState = cameraState,
                        modifier = Modifier.height((localconfig.screenHeightDp / 2).dp)
                    ) {
                        if (!isLoading) {
                            location?.let {
                                val latLng = LatLng(location.latitude, location.longitude)
                                cameraState.position = CameraPosition(latLng, 10f, 0f, 0f)
                                Marker(position = latLng)
                            }
                        }
                    }
                    if (isLoading) {
                        CircularProgressIndicator()
                    }
                }
                Column(Modifier.padding(16.dp)) {
                    Text(text = "City", modifier = Modifier.fillMaxWidth())
                    Text(text = "Region", modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun Map() {

}


/**
 * Задний фон с волной
 * @TODO: мигрировать в Canvas
 */
@Composable
fun Background(content: @Composable () -> Unit) {
    val isLight = MaterialTheme.colors.isLight

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column {
            Image(
                painter = painterResource(id = if (isLight) R.drawable.ic_wave_light else R.drawable.ic_wave_dark),
                contentDescription = null,
                modifier = Modifier.padding(top = 224.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary),
                content = { }
            )
        }
        content()
    }
}