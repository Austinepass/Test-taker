package com.orgustine.testtaker.presentation

import com.orgustine.testtaker.presentation.navigation.MainAppView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainAppView()
        }
    }
}

