package com.jquiros.weatherapp.domain.weather

data class WeatherInfo(
    val dataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
