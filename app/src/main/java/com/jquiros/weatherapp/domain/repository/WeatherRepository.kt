package com.jquiros.weatherapp.domain.repository

import com.jquiros.weatherapp.domain.util.Resource
import com.jquiros.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}