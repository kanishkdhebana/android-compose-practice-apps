package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FavouriteDao
import com.example.flightsearch.data.AirportDao
import com.example.flightsearch.data.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val airportDao: AirportDao,
    private val favouriteDao: FavouriteDao
) : ViewModel() {

    fun getAllAirports(): Flow<List<Airport>> =
        airportDao.getAllAirports()

    fun getFavouriteAirports(): Flow<List<Favorite>> =
        favouriteDao.getAllFavourites()


    private val _departureName = MutableStateFlow<String?>(null)
    val departureName: StateFlow<String?> = _departureName

    private val _arrivalName = MutableStateFlow<String?>(null)
    val arrivalName: StateFlow<String?> = _arrivalName

    fun fetchAirportNames(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            _departureName.value = getAirportName(departureCode)
            _arrivalName.value = getAirportName(destinationCode)
        }
    }

    private suspend fun getAirportName(iataCode: String): String =
        airportDao.getAirportName(iataCode)


    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                val airportDao = application.database.airportDao()
                val favouriteDao = application.database.favouriteDao()
                FlightSearchViewModel(airportDao, favouriteDao)
            }
        }
    }
}