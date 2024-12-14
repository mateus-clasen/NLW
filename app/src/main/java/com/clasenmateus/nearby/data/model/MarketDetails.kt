package com.clasenmateus.nearby.data.model

import kotlinx.serialization.Serializable

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
@Serializable
data class MarketDetails(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val rules: List<Rule>,
    val coupons: Int,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val cover: String
)
