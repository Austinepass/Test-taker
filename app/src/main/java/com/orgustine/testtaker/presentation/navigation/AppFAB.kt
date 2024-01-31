package com.orgustine.testtaker.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.orgustine.testtaker.util.HOME_ROUTE

@Composable
fun AppFAB(route: String?, onClick: () -> Unit) {
    if (route != HOME_ROUTE) return
    FloatingActionButton(
        shape = RoundedCornerShape(16.dp),
        containerColor = Color.Gray,
        modifier = Modifier
            .width(128.dp)
            .width(28.dp)
            .padding(16.dp),
        elevation = elevation(defaultElevation = 16.dp),
        onClick = onClick
    ) {
        Text(text = "Learn", color = Color.White)
    }
}