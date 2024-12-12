package com.clasenmateus.nlw.data.model

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */

data class Market (
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val coupons: Int,
    val rules: List<Rule> = emptyList(),
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val cover: String,
)