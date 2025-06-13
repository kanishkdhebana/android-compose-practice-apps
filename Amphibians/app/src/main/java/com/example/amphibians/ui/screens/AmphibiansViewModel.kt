package com.example.amphibians.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    data object Error : AmphibiansUiState
    data object Loading : AmphibiansUiState
}

class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansRepository
): ViewModel() {
     var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    @SuppressLint("SuspiciousIndentation")
    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            Log.d("AmphibiansViewModel", "Fetching amphibians...")
            amphibiansUiState = try {
                val amphibians = amphibiansRepository.getAmphibians()
                   Log.d("AmphibiansViewModel", "Successfully fetched amphibians: $amphibians")
                AmphibiansUiState.Success(amphibians)
            } catch (e: IOException) {
                Log.e("AmphibiansViewModel", "IOException while fetching amphibians", e)
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                Log.e("AmphibiansViewModel", "HttpException while fetching amphibians", e)
                AmphibiansUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}
