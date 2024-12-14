package com.clasenmateus.nearby.ui.screen.market_details

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
sealed class MarketDetailsUiEvent {
    data class OnFetchRules(val marketId: String) : MarketDetailsUiEvent()
    data class OnFetchCoupons(val qrCodeContent: String) : MarketDetailsUiEvent()
    data object OnResetCoupon : MarketDetailsUiEvent()

}