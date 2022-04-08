package com.lffq.weatherapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.lffq.weatherapp.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainView() {

    // Ссылка на ViewModel
    val vm = getViewModel<MainViewModel>()
    // Наблюдаем за LiveData
    val weather by vm.weather.observeAsState()


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${weather?.main?.temp} C°",
                style = TextStyle(fontSize = 32.sp)
            )
        }
    }

}



