package com.clasenmateus.nlw.data.model

import androidx.annotation.DrawableRes

/**
 * Created by Mateus H. Clasen on 11/12/2024.
 */
data class NearbyCategory(
    val id: String,
    val name: String
){
    @get:DrawableRes
    val icon: Int?
    get() = NearbyCategoryFilterChipView.fromDescription(description = name)?.icon
}
