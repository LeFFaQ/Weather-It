package com.lffq.weatherapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.lffq.weatherapp.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainView() {

    // Ссылка на ViewModel
    val vm = getViewModel<MainViewModel>()
    // Наблюдаем за LiveData
    val weather by vm.weather.observeAsState()
    val icon by vm.currentIcon.observeAsState()
    val dataLoaded by vm.dataLoaded.observeAsState()



    Column(modifier = Modifier.fillMaxSize()) {
        weather?.let {
            TopBar(searchCity = vm.searchCity, onSearchCityChanged = { vm.onSearchCityChanged(it) })
            CenterBar(
                temperature = weather?.main?.temp!!,
                city = weather?.name!!,
                date = vm.getFormattedDate(),
                icon = icon!!
            )
            BottomBar()
        }

    }
}

@Composable
fun TopBar(searchCity: String, onSearchCityChanged: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
        elevation = 24.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .background(Color(0xFF217DE9))
                .padding(bottom = 12.dp, end = 16.dp, start = 16.dp, top = 12.dp)
        ) {
            OutlinedTextField(
                colors = TextFieldDefaults
                    .outlinedTextFieldColors(
                        backgroundColor = Color.White
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
            Text(text = "${temperature.toInt()}°C", style = TextStyle(fontSize = 36.sp))
            Text(text = city)
            Text(text = date)
        }
    }
}

@Composable
fun BottomBar() {

    Card(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        elevation = 24.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFF217DE9))
        ) {
            ForecastRow()
            OtherDataColumn()
        }
    }
}

@Composable
fun ForecastRow() {

}

@Composable
fun OtherDataColumn() {

}


