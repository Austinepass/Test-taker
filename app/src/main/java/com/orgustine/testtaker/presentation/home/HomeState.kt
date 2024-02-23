package com.orgustine.testtaker.presentation.home

data class HomeState(
    val isTextFieldError: Boolean = false,
    val textFieldValue: String = ""
)
