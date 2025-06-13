package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport")
    fun getAllAirports(): Flow<List<Airport>>

    @Query("SELECT name FROM airport WHERE iata_code = :iataCode")
    suspend fun getAirportName(iataCode: String): String
}

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favorite")
    fun getAllFavourites(): Flow<List<Favorite>>
}
