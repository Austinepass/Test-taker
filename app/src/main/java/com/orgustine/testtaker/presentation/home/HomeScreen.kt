package com.orgustine.testtaker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.orgustine.testtaker.R
import com.orgustine.testtaker.util.PRACTICE_ROUTE

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        GreetingView(
            uiState = state,
            onValueChange = viewModel::updateTextField,
            onGenerateQuestions = { viewModel.generateQuestions(onNavigate) })
        TopicsView(onTopicSelected = { onNavigate(it) })
    }
}

@Composable
private fun GreetingView(
    uiState: HomeState,
    onValueChange: (String) -> Unit,
    onGenerateQuestions: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
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
        OutlinedTextField(
            value = uiState.textFieldValue,
            onValueChange = onValueChange,
            label = null,
            isError = uiState.isTextFieldError,
            supportingText = {
                if (uiState.isTextFieldError) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Input a topic to continue!"
                    )
                }
            },
            modifier = Modifier
                .border(1.dp, Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth(0.8f),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        )
        AssistChip(
            onClick = {
                keyboardController?.hide()
                onGenerateQuestions()
            },
            label = { Text("Generate Questions") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.magic),
                    tint = Color.DarkGray,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    }
}

@Composable
private fun TopicsView(onTopicSelected: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(start = 32.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Examples",
                style = TextStyle(textDecoration = TextDecoration.Underline, fontSize = 18.sp),
                fontWeight = FontWeight.Bold
            )
        }
        val interactionSource = remember { MutableInteractionSource() }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.FixedSize(165.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(8.dp)
        ) {
            items(topics) {
                Image(
                    painter = painterResource(it.second),
                    contentDescription = "topic image",
                    modifier = Modifier
                        .size(165.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onTopicSelected("$PRACTICE_ROUTE/{${it.first}}")
                        }
                )

            }
        }
    }
}

@Composable
private fun TopicCard(
    modifier: Modifier = Modifier,
    title: String,
    resourceId: Int
) {
    Image(
        painter = painterResource(resourceId),
        contentDescription = "topic image",
        modifier = Modifier
            .wrapContentSize()
            .zIndex(1f)
    )
    ElevatedCard(
        shape = RoundedCornerShape(15),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
        modifier = modifier.size(160.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private val topics = listOf(
    Pair("Sports", R.drawable.sports),
    Pair("Chemistry", R.drawable.chemistry),
    Pair("Math", R.drawable.math),
    Pair("History", R.drawable.history),
    Pair("Biology", R.drawable.biological),
    Pair("Geography", R.drawable.geography),
)

@Composable
// @PreviewLightDark
@Preview(showBackground = true)
fun HomePreview() {
    HomeScreen { }
}
