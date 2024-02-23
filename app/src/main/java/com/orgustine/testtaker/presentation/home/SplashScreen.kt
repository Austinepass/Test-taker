package com.orgustine.testtaker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.orgustine.testtaker.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) { // custom splashscreen, TBD
    LaunchedEffect(Unit) {
        delay(4000)
        onNavigate()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = "splashscreen",
            modifier = Modifier.fillMaxSize()
        )
    }
}