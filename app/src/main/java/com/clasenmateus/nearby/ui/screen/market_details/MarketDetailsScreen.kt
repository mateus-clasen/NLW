package com.clasenmateus.nearby.ui.screen.market_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.clasenmateus.nearby.R
import com.clasenmateus.nearby.data.model.Market
import com.clasenmateus.nearby.ui.component.button.NearbyButton
import com.clasenmateus.nearby.ui.component.market_details.NearbyMarketDetailsCoupons
import com.clasenmateus.nearby.ui.component.market_details.NearbyMarketDetailsInfos
import com.clasenmateus.nearby.ui.component.market_details.NearbyMarketDetailsRules
import com.clasenmateus.nearby.ui.theme.Typography

/**
 * Created by Mateus H. Clasen on 13/12/2024.
 */
@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: MarketDetailsUiState,
    onEvent: (MarketDetailsUiEvent) -> Unit,
    market: Market,
    onNavigateToQrCodeScanner: () -> Unit,
    onNavigateBack: () -> Unit = {}
) {
    LaunchedEffect(true) {
        onEvent(MarketDetailsUiEvent.OnFetchRules(marketId = market.id))
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Imagem do Local",
            contentScale = ContentScale.Crop,
            model = market.cover
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(36.dp)
            ) {
                Column {
                    Text(text = market.name, style = Typography.headlineLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = market.description, style = Typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(48.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    NearbyMarketDetailsInfos(market = market)
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(vertical = 24.dp)
                            .fillMaxWidth()
                    )
                    if (!uiState.rules.isNullOrEmpty()) {
                        NearbyMarketDetailsRules(rules = uiState.rules)
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = 24.dp)
                                .fillMaxWidth()
                        )
                    }
                    if (!uiState.coupon.isNullOrEmpty())
                    NearbyMarketDetailsCoupons(
                        coupons = listOf(uiState.coupon)
                    )
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = "Ler QR Code",
                    onClick = onNavigateToQrCodeScanner
                )
            }
        }
        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = {
                onNavigateBack()
            }
        )
    }
}