package com.clasenmateus.nearby.ui.screen

import com.clasenmateus.nearby.data.model.Category
import com.clasenmateus.nearby.data.model.Market
import com.google.android.gms.maps.model.LatLng

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
data class HomeUiState(
    val categories: List<Category>? = null,
    val markets: List<Market>? = null,
    val marketLocation: List<LatLng>? = null

)
