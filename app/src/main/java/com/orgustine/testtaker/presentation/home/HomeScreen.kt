package com.orgustine.testtaker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orgustine.testtaker.R

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingView()
        TopicsView()
    }
}

@Composable
private fun GreetingView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 128.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Hi, Scholar",
            fontSize = 32.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "What would you like to practise today?",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
        )
    }
}

@Composable
private fun TopicsView() {
    LazyRow {
        items(topics) {
            TopicCard(
                modifier = Modifier.padding(16.dp),
                title = it.first,
                resourceId = it.second
            )
        }
    }
}

@Composable
private fun TopicCard(
    modifier: Modifier = Modifier,
    title: String,
    resourceId: Int
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(300.dp)
                .padding(8.dp),
            painter = painterResource(resourceId),
            contentDescription = "topic image"
        )
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

private val topics = listOf(
    Pair("Verbal Reasoning", R.drawable.verbal),
    Pair("Quantitative Reasoning", R.drawable.verbal),
    Pair("Abstract Reasoning", R.drawable.verbal),
)

@Composable
@PreviewLightDark
@Preview(showBackground = true)
fun HomePreview() {
    HomeScreen { }
}
