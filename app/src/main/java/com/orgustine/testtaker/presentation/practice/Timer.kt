package com.orgustine.testtaker.presentation.practice

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orgustine.testtaker.presentation.ui.theme.Grey10
import com.orgustine.testtaker.presentation.ui.theme.MidBlue

@Composable
fun Timer(
    modifier: Modifier,
    progress: Float,
    numberStyle: TextStyle = TextStyle(
        color = Color.Gray,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    size: Dp = 52.dp,
    indicatorThickness: Dp = 5.dp,
    foregroundColor: Color = MidBlue,
    backgroundColor: Color = Grey10
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        Canvas(
            modifier = Modifier.size(size)
        ) {

            drawCircle(
                color = backgroundColor,
                radius = size.toPx() / 2,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = foregroundColor,
                startAngle = -90f,
                sweepAngle = progress * 360,
                useCenter = false,
                style = Stroke(indicatorThickness.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (progress * 30).toInt().toString(),
            style = numberStyle
        )
    }
}