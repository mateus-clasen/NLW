package com.clasenmateus.nearby.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clasenmateus.nearby.ui.component.category.NearbyCategoryFilterChipList
import com.clasenmateus.nearby.ui.component.market.NearbyMarketCardList
import com.clasenmateus.nearby.ui.theme.Gray100
import com.google.maps.android.compose.GoogleMap

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpen by remember { mutableStateOf(true) }

    val configuration = LocalConfiguration.current

    if (isBottomSheetOpen) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetState,
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                NearbyMarketCardList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    markets = emptyList(),
                    onMarketClick = { }
                )
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize()
                    )
                    NearbyCategoryFilterChipList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .align(Alignment.TopStart),
                        categories = emptyList(),
                        onSelectedCategoryChanged = {

                        }
                    )
                }

            }
        )
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}