package com.lffq.weatherapp.view

import android.os.Build
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
import com.lffq.weatherapp._dayIcons
import com.lffq.weatherapp._nightIcons
import com.lffq.weatherapp.network.models.onecall.DailyItem
import com.lffq.weatherapp.network.models.onecall.OneCallModel
import com.lffq.weatherapp.viewmodel.MainViewModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaZoneId
import org.koin.androidx.compose.getViewModel
import java.time.Instant
import java.time.format.DateTimeFormatter

@Composable
fun MainView() {

    // Ссылка на ViewModel
    val vm = getViewModel<MainViewModel>()
    // Наблюдаем за LiveData
    val weather by vm.weather.observeAsState()
    val icon by vm.currentIcon.observeAsState()
    val forecast by vm.forecast.observeAsState()
    val dataLoaded by vm.dataLoaded.observeAsState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        weather?.let {
            TopBar(searchCity = vm.searchCity, onSearchCityChanged = { vm.onSearchCityChanged(it) })
            CenterBar(
                temperature = weather?.main?.temp!!,
                city = weather?.name!!,
                date = vm.getFormattedDate(),
                icon = icon!!
            )
            forecast?.let { it1 -> BottomBar(it1) }
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

    fun _chooseIcon(icon: String): Int {
        return if ("n" in icon) {
            _nightIcons[icon]!!
        } else {
            _dayIcons[icon]!!
        }
    }

    fun _fromUnix(instant: Long): String {
        val systemTZ = TimeZone.currentSystemDefault()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val unix: Instant = Instant.ofEpochSecond(instant)

            val formatter = DateTimeFormatter.ofPattern("E d")
            return formatter.format(unix.atZone(systemTZ.toJavaZoneId()))
        }

        return "Some date at Build.VERSION_CODES.O"
    }



    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(_chooseIcon(item.weather!![0]!!.icon!!)))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(_fromUnix(item.dt!!.toLong()))
        LottieAnimation(composition = composition, progress = progress, modifier = Modifier.size(24.dp))
        Text("${item.temp?.day?.toInt()}°/${item.temp?.night?.toInt()}°")
    }
}

@Composable
fun OtherDataColumn() {
}


