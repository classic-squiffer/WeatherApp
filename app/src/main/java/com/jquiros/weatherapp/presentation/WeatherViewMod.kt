package com.jquiros.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jquiros.weatherapp.domain.location.LocationTracker
import com.jquiros.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.jquiros.weatherapp.domain.util.Resource
import kotlinx.coroutines.launch


@HiltViewModel
class WeatherViewMod @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker

): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set
    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null,
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null,
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Could not retrieve locatione :("
                )
            }
        }
    }
}