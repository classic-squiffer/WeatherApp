package com.jquiros.weatherapp.data.repository

import com.jquiros.weatherapp.data.mappers.toWeatherInfo
import com.jquiros.weatherapp.data.remote.WeatherAPI
import com.jquiros.weatherapp.domain.repository.WeatherRepository
import com.jquiros.weatherapp.domain.util.Resource
import com.jquiros.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: WeatherAPI
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "error lol")
        }
    }
}