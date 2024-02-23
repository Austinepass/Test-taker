package com.orgustine.testtaker.presentation.practice

import com.orgustine.testtaker.data.remote.Quiz
import com.orgustine.testtaker.util.AnimationType

data class PracticeState(
    val questions: List<Quiz> = emptyList(),
    val prompt: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentQuiz: Quiz? = null,
    val selectedOption: String = "",
    val progress: Float = 0.1f,
    val timerProgress: Float = 0f,
    val score: Int = 0,
    val showDialog: Boolean = false,
    val destination: String = "",
    val animationType: AnimationType = AnimationType.Default
)
