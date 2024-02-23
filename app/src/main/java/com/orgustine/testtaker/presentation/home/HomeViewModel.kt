package com.orgustine.testtaker.presentation.home

import androidx.lifecycle.ViewModel
import com.orgustine.testtaker.util.PRACTICE_ROUTE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()


    fun generateQuestions(onNavigate: (String) -> Unit) {
        val topic = uiState.value.textFieldValue
        _uiState.update {
            it.copy(isTextFieldError = topic.isEmpty())
        }

        if (topic.isEmpty()) return
        onNavigate("$PRACTICE_ROUTE/$topic")
    }

    fun updateTextField(value: String) {
        _uiState.update {
            it.copy(textFieldValue = value)
        }
    }

}