package com.clasenmateus.nlw.data.model

import androidx.annotation.DrawableRes

/**
 * Created by Mateus H. Clasen on 11/12/2024.
 */
data class Category(
    val id: String,
    val name: String
){
    @get:DrawableRes
    val icon: Int?
    get() = CategoryFilterChipView.fromDescription(description = name)?.icon
}
