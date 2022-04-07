package com.lffq.weatherapp

import com.lffq.weatherapp.network.WeatherApi
import com.lffq.weatherapp.network.WeatherBuilder
import com.lffq.weatherapp.network.WeatherRepository
import com.lffq.weatherapp.network.WeatherRepositoryImpl
import com.lffq.weatherapp.network.models.current.WeatherModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*


import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RetrofitUnitTest {

    private lateinit var api: WeatherApi
    private lateinit var repository: WeatherRepository
    private lateinit var result: Response<WeatherModel>


    @Before
    fun prepareForTest() {
        val interceptor = WeatherBuilder.InterceptorInstance()
        val client = WeatherBuilder.OkHttpInstance(interceptor)

        val retrofit = WeatherBuilder.RetrofitInstance(client, "https://api.openweathermap.org")

        api = retrofit.create(WeatherApi::class.java)
        repository = WeatherRepositoryImpl(api)

        runBlocking {
            result = repository.getWeatherByCity("kemerovo", appid)
        }

    }

    @Test
    fun testResultIsEmpty() {
        assertNotEquals(result.body().toString(), "")
    }

    @Test
    fun testCityOfResults() {
        assertEquals(result.body()?.name, "Kemerovo")
    }

    @Test
    fun testCountryOfResults() {
        assertEquals(result.body()?.sys?.country, "RU")
    }
}

