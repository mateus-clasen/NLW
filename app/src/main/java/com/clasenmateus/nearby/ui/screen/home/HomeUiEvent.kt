package com.clasenmateus.nearby.ui.screen.home

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
sealed class HomeUiEvent {
    data object OnFetchCategories : HomeUiEvent()
    data class OnFetchMarkets(val categoryId: String) : HomeUiEvent()

}