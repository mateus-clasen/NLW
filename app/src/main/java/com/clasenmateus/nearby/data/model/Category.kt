package com.clasenmateus.nearby.data.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

/**
 * Created by Mateus H. Clasen on 11/12/2024.
 */
@Serializable
data class Category(
    val id: String,
    val name: String
){
    @get:DrawableRes
    val icon: Int?
    get() = CategoryFilterChipView.fromDescription(description = name)?.icon
}
