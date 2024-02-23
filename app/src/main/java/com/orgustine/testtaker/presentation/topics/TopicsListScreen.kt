package com.orgustine.testtaker.presentation.topics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopicsListScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Topic 1")
        Text(text = "Topic 2")
        Text(text = "Topic 3")
        Text(text = "Topic 4")
        Text(text = "Topic 5")
    }
}