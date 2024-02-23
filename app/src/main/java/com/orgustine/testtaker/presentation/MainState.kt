package com.orgustine.testtaker.presentation

import com.orgustine.testtaker.data.remote.Quiz

data class MainState(
    val topic: List<Quiz> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)
