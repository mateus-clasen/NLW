package com.clasenmateus.nearby.data.model

import kotlinx.serialization.Serializable

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */

@Serializable
data class Market (
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val coupons: Int,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val cover: String
)