package com.example.dessertrelease

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.dessertrelease.data.UserPreferencesRepository

private const val  LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)

class DessertReleaseApplication: Application() {
    lateinit var  userPreferenceRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        userPreferenceRepository = UserPreferencesRepository(dataStore)
    }
}