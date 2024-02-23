package com.orgustine.testtaker.presentation.practice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.orgustine.testtaker.BuildConfig
import com.orgustine.testtaker.R
import com.orgustine.testtaker.presentation.ui.theme.Gold10
import com.orgustine.testtaker.presentation.ui.theme.Grey10
import com.orgustine.testtaker.presentation.ui.theme.LightBlue
import com.orgustine.testtaker.presentation.ui.theme.MidBlue
import com.orgustine.testtaker.util.HOME_ROUTE

@Composable
fun PracticeScreen(
    topic: String?,
    viewModel: PracticeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.generateQuestions(BuildConfig.PRACTICE_PROMPT + topic)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        state.animationType.param?.let {
            AnimationScreen(
                type = state.animationType,
                onFinish = viewModel::onFinishAnimation,
                onClick = { viewModel.onClickAnimation(onNavigate) },
            )
        }

        if (state.showDialog) {
            QuizDialog(
                onCancel = viewModel::closeDialog,
                onConfirm = { viewModel.endQuiz(onNavigate) }
            )
        }
        TopContent(
            currentProgress = state.progress,
            onOpenDialog = viewModel::openDialog
        )
        MainContent(
            state = state,
            onNextQuestion = viewModel::updateProgress,
            onSelectOption = viewModel::selectOption
        )
    }
}

@Composable
fun MainContent(
    state: PracticeState,
    onNextQuestion: () -> Unit,
    onSelectOption: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ContentBody(state, onSelectOption)

        Button(
            onClick = { onNextQuestion() },
            shape = RoundedCornerShape(25),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(48.dp)
        ) {
            Text(
                text = if (state.progress >= 1f) "Finish Quiz" else "Next",
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun TopContent(
    currentProgress: Float,
    onOpenDialog: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Grey10),
            shape = CircleShape,
            modifier = Modifier.size(38.dp)
        ) {
            IconButton(onClick = { onOpenDialog(HOME_ROUTE) }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "clear"
                )
            }
        }
        PracticeProgress(currentProgress)
    }
}

@Composable
private fun PracticeProgress(currentProgress: Float) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Grey10),
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier.wrapContentHeight(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.spacedBy(8.dp)
        ) {
            LinearProgressIndicator(
                progress = { currentProgress },
                color = Gold10,
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth(0.85f)
                    .padding(start = 8.dp)
            )
            Text(
                text = "${(currentProgress * 10).toInt()}/10",
                color = MidBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ContentBody(
    state: PracticeState,
    onSelectOption: (String) -> Unit
) {
    state.currentQuiz?.let {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                Timer(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .zIndex(1f)
                        .clip(CircleShape)
                        .padding(4.dp)
                        .background(Color.White)
                        .align(Alignment.TopCenter),
                    progress = state.timerProgress
                )
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = LightBlue,
                    ),
                    border = BorderStroke(1.dp, Grey10),
                    shape = RoundedCornerShape(12),
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(0.9f)
                        .align(Alignment.Center),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = it.question,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(8.dp)
                                .wrapContentSize()
                        )
                    }
                }
            }
            Options(state, onSelectOption)
        }
    }

}

@Composable
fun Options(
    state: PracticeState,
    onSelectOption: (String) -> Unit,
) {
    state.currentQuiz?.let { quiz ->
        LazyColumn {
            items(quiz.optionsList) { option ->
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = when {
                            state.selectedOption.isNotEmpty() && option == quiz.answer -> MidBlue.copy(
                                alpha = 0.3f
                            )

                            state.selectedOption.isNotEmpty() && option == state.selectedOption -> Color.Red.copy(
                                alpha = 0.5f
                            )

                            else -> Color.White
                        },
                    ),
                    border = BorderStroke(1.dp, Grey10),
                    shape = RoundedCornerShape(25),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clickable { if (state.selectedOption.isEmpty()) onSelectOption(option) },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = option,
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(16.dp)
                        )
                        Icon(
                            painter = when {
                                state.selectedOption.isNotEmpty() && option == quiz.answer ->
                                    painterResource(id = R.drawable.opt_correct)

                                state.selectedOption.isNotEmpty() && option == state.selectedOption ->
                                    painterResource(id = R.drawable.opt_wrong)

                                else -> painterResource(id = R.drawable.opt_default)
                            },
                            contentDescription = "check",
                            modifier = Modifier.padding(16.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}