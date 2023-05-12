package com.jquiros.weatherapp.dependant

import androidx.compose.runtime.ExperimentalComposeApi
import com.jquiros.weatherapp.data.location.DefLocationTracker
import com.jquiros.weatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationMod {

    @OptIn(ExperimentalComposeApi::class)
    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: DefLocationTracker): LocationTracker

}