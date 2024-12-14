package com.clasenmateus.nearby.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.clasenmateus.nearby.R
import com.clasenmateus.nearby.ui.theme.GreenLight
import kotlinx.coroutines.delay

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigateToWelcome: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        onNavigateToWelcome()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GreenLight),
        ){
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(R.drawable.img_logo_logo_logo_text),
            contentDescription = "Splash Screen Text"
        )
        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.bg_splash_screen),
            contentDescription = "Splash Screen Background"
        )
    }
}