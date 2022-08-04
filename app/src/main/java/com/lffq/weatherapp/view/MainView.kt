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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.lffq.weatherapp.network.models.base.onecall.DailyItem
import com.lffq.weatherapp.network.models.base.onecall.OneCallModel
import com.lffq.weatherapp.usecases.date.GetFormattedDateUseCase
import com.lffq.weatherapp.usecases.date.GetTimeFromUnixUseCase
import com.lffq.weatherapp.usecases.other.GetIconFromIdUseCase
import com.lffq.weatherapp.viewmodel.MainViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun MainView() {

    // Ссылка на ViewModel
    val vm = getViewModel<MainViewModel>()
    // Наблюдаем за LiveData
    //val weather by vm.weather.observeAsState()
    //val icon by vm.currentIcon.observeAsState()
    val forecast by vm.forecast.observeAsState()
    val dataLoaded by vm.dataLoaded.observeAsState()


    forecast?.let {

        val icon = get<GetIconFromIdUseCase>()
            .execute(it.current.weather[0].icon)

        val date = get<GetFormattedDateUseCase>()
            .execute()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            TopBar(searchCity = vm.searchCity, onSearchCityChanged = { vm.onSearchCityChanged(it) })
            CenterBar(
                temperature = it.current.temp,
                city = "${it.lat}",
                date = date,
                icon = icon
            )
            BottomBar(it)
        }
    }
}

@Composable
fun TopBar(searchCity: String, onSearchCityChanged: (String) -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
        elevation = 24.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .padding(bottom = 12.dp, end = 16.dp, start = 16.dp, top = 12.dp)
        ) {
            OutlinedTextField(
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxSize(),
                value = searchCity,
                onValueChange = { onSearchCityChanged(it) },
                singleLine = true

            )
        }
    }
}

@Composable
fun CenterBar(temperature: Number, city: String, date: String, icon: Int) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(icon))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        LottieAnimation(composition = composition, progress, modifier = Modifier.size(128.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 6.dp)
        ) {
            Text(
                text = "${temperature.toInt()}°C",
                style = TextStyle(fontSize = 36.sp, color = MaterialTheme.colors.onBackground)
            )
            Text(text = city, color = MaterialTheme.colors.onBackground)
            Text(text = date, color = MaterialTheme.colors.onBackground)
        }
    }
}

@Composable
fun BottomBar(forecast: OneCallModel) {

    val scrollState = rememberScrollState()

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        elevation = 24.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)) {
                ForecastRow(forecast)
                OtherDataColumn()
            }
        }
    }
}

@Composable
fun ForecastRow(forecast: OneCallModel) {
    Card(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            for (item in forecast.daily!!) {
                ForecastRowItem(item = item!!)
            }
        }
    }
}



@Composable
fun ForecastRowItem(item: DailyItem) {

    val unixTime = get<GetTimeFromUnixUseCase>()
        .execute(item.dt)

    val icon = get<GetIconFromIdUseCase>()
        .execute(item.weather[0].icon)

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(icon))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(unixTime)
        LottieAnimation(composition = composition, progress = progress, modifier = Modifier.size(24.dp))
        Text("${item.temp?.day?.toInt()}°/${item.temp?.night?.toInt()}°")
    }
}

@Composable
fun OtherDataColumn() {
}
