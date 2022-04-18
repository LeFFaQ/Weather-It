package com.lffq.weatherapp.view

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lffq.weatherapp.R
import com.lffq.weatherapp.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainView() {

    // Ссылка на ViewModel
    val vm = getViewModel<MainViewModel>()
    // Наблюдаем за LiveData
    val weather by vm.weather.observeAsState()
    val imgLoaded by vm.imageLoaded.observeAsState()
    val dataLoaded by vm.dataLoaded.observeAsState()

    Scaffold {
        if (imgLoaded == true && dataLoaded == true) {
            Column(modifier = Modifier.fillMaxSize()) {
                weather?.let {
                    LocationRow(city = it.name)
                    MainRow(temp = it.main.temp, bitmap = vm.bitmap.value!!)
                    MiscRow(humidity = it.main.humidity, wind = it.wind.speed, pressure = it.main.pressure)
                }
            }
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

    }

}

/**
 * Строка сверху.
 * Выводит город и дату формата:
 * "<День недели>, <Сокр. название месяца> <День месяца>, <4-значный год>"
 */
@Composable
fun LocationRow(city: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = city)
        Text(text = "ddd, MMM d, yyyy")
    }
}

/**
 * Основная строка.
 * Выводит показания температуры
 * и инонку описания (пасмурно, солнечно и т.д)
 */
@Composable
fun MainRow(temp: Number, bitmap: Bitmap) {
    Column {
        Image(painter = rememberAsyncImagePainter(bitmap), contentDescription = "Status")
        Text(text = "1c")
    }
}

/**
 * Дополнительная строка.
 * Выводит показания давления,
 * влажности и ветра
 */
@Composable
fun MiscRow(humidity: Number, wind: Number, pressure: Number) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround) {
        MiscRowItem(icon = R.drawable.humidity_48, value = "${humidity}%")
        MiscRowItem(icon = R.drawable.wind_48, value = "${wind}M/S")
        MiscRowItem(icon = R.drawable.pressure_48, value = "${pressure}hPa")
    }
}

/**
 * Конкретный элемент из MicsRow
 */
@Composable
fun MiscRowItem(@DrawableRes icon: Int, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .
    ) {
        Image(painter = painterResource(id = icon), contentDescription = "icon")
        Text(text = value)
    }
}

