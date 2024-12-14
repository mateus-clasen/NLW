package com.clasenmateus.nearby.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.clasenmateus.nearby.R
import com.clasenmateus.nearby.data.model.Market
import com.clasenmateus.nearby.ui.component.category.NearbyCategoryFilterChipList
import com.clasenmateus.nearby.ui.component.market.NearbyMarketCardList
import com.clasenmateus.nearby.ui.theme.Gray100
import com.clasenmateus.nearby.ui.util.findNortheastPoint
import com.clasenmateus.nearby.ui.util.findSouthwestPoint
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import kotlin.math.roundToInt

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMarketDetails: (Market) -> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()

    val configuration = LocalConfiguration.current
    LaunchedEffect(true) {
        onEvent(HomeUiEvent.OnFetchCategories)
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetState,
        sheetContainerColor = Gray100,
        sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            if (!uiState.markets.isNullOrEmpty())
                NearbyMarketCardList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    markets = uiState.markets,
                    onMarketClick = { selectedMarket ->
                        onNavigateToMarketDetails(selectedMarket)
                    }
                )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it
                            .calculateBottomPadding()
                            .minus(8.dp)
                    )
            ) {
                NearbyGoogleMap(uiState = uiState)
                if (!uiState.categories.isNullOrEmpty())
                    NearbyCategoryFilterChipList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .align(Alignment.TopStart),
                        categories = uiState.categories,
                        onSelectedCategoryChanged = { selectedCategory ->
                            onEvent(HomeUiEvent.OnFetchMarkets(categoryId = selectedCategory.id))
                        }
                    )
            }
        }
    )
}

@Composable
fun NearbyGoogleMap(modifier: Modifier = Modifier, uiState: HomeUiState) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(), 13f)
    }
    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    ) {
        context.getDrawable(R.drawable.ic_user_location)?.let { drawable ->
            Marker(
                state = MarkerState(position =),
                icon = BitmapDescriptorFactory.fromBitmap(
                    drawable.toBitmap(
                        width = density.run { 72.dp.toPx() }.roundToInt(),
                        height = density.run { 72.dp.toPx() }.roundToInt()
                    )
                )
            )
        }
    }

    if (!uiState.markets.isNullOrEmpty()) {
        context.getDrawable(R.drawable.img_pin)?.let { drawable ->
            uiState.marketLocation?.toImmutableList()
                ?.forEachIndexed { index, location ->
                    Marker(
                        state = MarkerState(position = location),
                        icon = BitmapDescriptorFactory.fromBitmap(
                            drawable.toBitmap(
                                width = density.run { 36.dp.toPx() }.roundToInt(),
                                height = density.run { 36.dp.toPx() }.roundToInt()
                            )
                        ),
                        title = uiState.markets[index].name
                    )
                }.also {
                    coroutineScope.launch {
                        val allMarks = uiState.marketLocation?.plus()
                        val southwestPoint =
                            findSouthwestPoint(points = allMarks.orEmpty())
                        val northeastPoint =
                            findNortheastPoint(points = allMarks.orEmpty())

                        val centerPointLatitude =
                            (southwestPoint.latitude + northeastPoint.latitude) / 2
                        val centerPointLongitude =
                            (southwestPoint.longitude + northeastPoint.longitude) / 2

                        val cameraUpdate =
                            CameraUpdateFactory.newCameraPosition(
                                CameraPosition(
                                    LatLng(
                                        centerPointLatitude,
                                        centerPointLongitude,
                                    ),
                                    13f,
                                    0f,
                                    0f
                                )
                            )
                        delay(200)
                        cameraPositionState.animate(cameraUpdate, durationMs = 500)
                    }
                }
        }
    }
}