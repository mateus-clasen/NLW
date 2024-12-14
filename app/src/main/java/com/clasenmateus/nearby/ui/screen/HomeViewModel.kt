package com.clasenmateus.nearby.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clasenmateus.nearby.core.network.NearbyRemoteDataSource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun fetchCategories() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                NearbyRemoteDataSource.getCategories().fold(
                    onSuccess = { categories ->
                        currentUiState.copy(categories = categories)
                    },
                    onFailure = { _ ->
                        currentUiState.copy(
                            categories = emptyList()
                        )
                    }
                )
            }
        }
    }

    fun fetchMarkets(categoryId: String) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                NearbyRemoteDataSource.getMarkets(categoryId = categoryId).fold(
                    onSuccess = { markets ->
                        currentUiState.copy(
                            markets = markets,
                            marketLocation = markets.map { market ->
                                LatLng(market.latitude, market.longitude)
                            }
                        )
                    },
                    onFailure = { _ ->
                        currentUiState.copy(
                            marketLocation = emptyList(),
                            markets = emptyList()
                        )
                    }
                )
            }
        }
    }
}

