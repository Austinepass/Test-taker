package com.orgustine.testtaker.presentation.practice

data class ProgressState(
    val currentProgress: Float = 0.5f,
    val currentQuestion: Int = 1,
    val currentTime: Int = 5
)