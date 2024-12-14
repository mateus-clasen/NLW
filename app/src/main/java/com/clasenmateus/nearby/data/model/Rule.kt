package com.clasenmateus.nearby.data.model

import kotlinx.serialization.Serializable

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */
@Serializable
data class Rule (
    val id: String,
    val description: String,
    val marketId: String,
)