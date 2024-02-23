package com.orgustine.testtaker.presentation.practice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.orgustine.testtaker.R
import com.orgustine.testtaker.util.AnimationType

@Composable
fun SummaryScreen(
    score: Int?,
    onNavigate: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val params = AnimationType.Summary.param!!
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(params.animationId))
        LottieAnimation(
            composition = composition,
            iterations = params.iteration,
            clipSpec = params.clipSpec,
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = stringResource(R.string.congratulations),
            fontSize = 28.sp,
            modifier = Modifier.padding(8.dp)
        )
        score?.let {
            Text(
                text = stringResource(id = params.msgId, it),
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        params.btnTextId?.let {
            Button(
                onClick = onNavigate,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp)
            ) {
                Text(text = stringResource(it), textAlign = TextAlign.Center)
            }
        }
    }
}