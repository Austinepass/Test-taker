package com.orgustine.testtaker.presentation.practice

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.orgustine.testtaker.R

@Composable
fun QuizDialog(onCancel: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(stringResource(R.string.finish_quiz)) },
        text = { Text(stringResource(R.string.finish_quiz_msg)) },
        dismissButton = {
            TextButton(
                onClick = onCancel,
                content = { Text(text = stringResource(R.string.cancel)) }
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(R.string.confirm), color = Color.Red)
            }
        }
    )
}