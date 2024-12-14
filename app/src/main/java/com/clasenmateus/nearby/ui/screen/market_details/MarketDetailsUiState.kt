package com.clasenmateus.nearby.ui.screen.market_details

import com.clasenmateus.nearby.data.model.Rule

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupons: String? = null
)
