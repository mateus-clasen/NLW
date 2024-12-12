package com.clasenmateus.nlw.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import com.clasenmateus.nlw.R
import com.clasenmateus.nlw.ui.theme.GreenLight

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
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