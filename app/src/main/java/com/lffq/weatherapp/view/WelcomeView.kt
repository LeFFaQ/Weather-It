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

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lffq.weatherapp.R
import com.lffq.weatherapp.network.models.geocoding.GeocodingModelItem
import com.lffq.weatherapp.ui.navigation.Screen
import com.lffq.weatherapp.viewmodel.WelcomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun WelcomeView(navController: NavController) {

    val vm = getViewModel<WelcomeViewModel>()

    val cities by vm.cities.observeAsState()

    Background {
        Box(modifier = Modifier.fillMaxSize()) {
            Title(Modifier.align(Alignment.TopCenter))
            CityChooser(
                city = vm.city,
                onChange = { vm.onQueryCity(it) },
                cities = cities,
                modifier = Modifier.align(Alignment.Center)
            )
            Button(
                onClick = {
                    vm.saveCityToDS()
                    navController.navigate(Screen.Main.route)

                          },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    contentColor = MaterialTheme.colors.onSurface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
            ) {
                Text(text = "Продолжить")
            }
        }
    }
}


@Composable
fun Title(modifier: Modifier) {
    Column(modifier = modifier.padding(top = 48.dp)) {
        Text(
            text = "Прежде чем мы начнем",
            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground)
        )
        Text(text = "Укажите ваш город", style = TextStyle(fontSize = 24.sp, color = MaterialTheme.colors.onBackground))
    }
}

@Composable
fun CityChooser(
    city: String,
    onChange: (String) -> Unit,
    modifier: Modifier,
    cities: List<GeocodingModelItem?>?,
) {
    val configuration = LocalConfiguration.current


    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
        elevation = 24.dp,
        modifier = modifier
            .fillMaxWidth()
            .height((configuration.screenHeightDp / 2).dp)
            .padding(start = 24.dp, end = 24.dp)

    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = city,
                onValueChange = onChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        Log.d(TAG, "CityChooser: ${city}")
                    }
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            )
            cities?.let {
                LazyColumn {
                    items(cities) { city ->
                        CityItem(city!!)
                    }
                }
            }
        }
    }
}

@Composable
fun CityItem(item: GeocodingModelItem) {

    val vm = getViewModel<WelcomeViewModel>()

    var isSelected by remember {
        mutableStateOf(false)
    }

    Log.d(TAG, "CityItem: ${item.name}, ${item.country}")
    Row(Modifier
        .fillMaxWidth()
        .clickable {
            isSelected = !isSelected
        }
        .padding(all = 16.dp)
    ) {

        Column(
            Modifier
                .fillMaxWidth(0.8f)
        ) {
            Text(text = item.name!!)
            Text(text = item.country!!)
        }
        if (isSelected) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier
                        .size(42.dp)
                        .fillMaxWidth(0.2f)
                )
            }
            vm.setSelectedCity(item)
        }
    }
}

/**
 * Задний фон с волной
 * @TODO: мигрировать в Canvas
 */
@Composable
fun Background(content: @Composable () -> Unit) {
    val isLight = MaterialTheme.colors.isLight

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colors.background)) {
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