package com.clasenmateus.nearby.core.network

import com.clasenmateus.nearby.core.network.KtorHttpClient.httpClientAndroid
import com.clasenmateus.nearby.data.model.Category
import com.clasenmateus.nearby.data.model.Coupon
import com.clasenmateus.nearby.data.model.Market
import com.clasenmateus.nearby.data.model.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

/**
 * Created by Mateus H. Clasen on 14/12/2024.
 */
object NearbyRemoteDataSource {

    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val LOCAL_HOST_PHYSICAL_BASE_URL = "http://192.168.2.186:3333"

    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL

    // - busca de categorias
    // - busca de locais (com base em uma categoria)
    // - busca de detalhes de um local (com base em um local especifico)
    // - gerar cupom a partir da leitura do qrcode

    suspend fun getCategories(): Result<List<Category>> = try {
        val categories = httpClientAndroid.get("$BASE_URL/categories").body<List<Category>>()
        Result.success(categories)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val markets: List<Market> =
            httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}").body<List<Market>>()
        Result.success(markets)
    }
    catch (e: Exception) {
        Result.failure(e)
    }
    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {
        val market = httpClientAndroid.get("$BASE_URL/markets/${marketId}").body<MarketDetails>()
        Result.success(market)
    }
    catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon> = try {
        val coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}").body<Coupon>()
        Result.success(coupon)
    }
    catch (e: Exception) {
        Result.failure(e)
    }

}