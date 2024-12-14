package com.clasenmateus.nearby.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
object KtorHttpClient {
    private const val NETWORK_TIMEOUT = 5_000L

    val httpClientAndroid by lazy {
        HttpClient(Android) {
            install(ContentNegotiation){
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        useAlternativeNames = true
                        explicitNulls = true
                        encodeDefaults = false
                        useArrayPolymorphism = true
                    }
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = NETWORK_TIMEOUT
                connectTimeoutMillis = NETWORK_TIMEOUT
                socketTimeoutMillis = NETWORK_TIMEOUT

            }
            install(Logging){
                level = LogLevel.ALL
            }
        }
    }
}