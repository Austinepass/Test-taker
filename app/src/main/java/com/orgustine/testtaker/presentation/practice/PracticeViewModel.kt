package com.orgustine.testtaker.presentation.practice

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orgustine.testtaker.domain.repository.QuizRepository
import com.orgustine.testtaker.util.AnimationType.*
import com.orgustine.testtaker.util.HOME_ROUTE
import com.orgustine.testtaker.util.Resource
import com.orgustine.testtaker.util.SUMMARY_ROUTE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(PracticeState())
    val uiState = _uiState.asStateFlow()

    private var countdownJob: Job? = null

    fun generateQuestions(prompt: String?) {
        if (prompt == null) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, prompt = prompt, animationType = Loading) }
            delay(5000)
            try {
                when (val response = quizRepository.getQuestions(prompt)) {
                    is Resource.Success -> {
                        val data = response.data
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                animationType = LoadingSuccess,
                                questions = data?.questions ?: emptyList(),
                                currentQuiz = data?.questions?.get((0)),
                                error = if (data != null) null else "That was unexpected!"
                            )
                        }
                    }

                    else -> _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message,
                            animationType = LoadingFailure,
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message,
                        animationType = LoadingFailure
                    )
                }
                Log.e("PracticeViewModel", "generateQuestions error: $e")
            }
        }
    }

    fun selectOption(option: String, autoSelect: Boolean = false) {
        _uiState.update {
            it.copy(
                selectedOption = option,
                score = if (!autoSelect && option == it.currentQuiz?.answer) it.score + 1 else it.score
            )
        }
        countdownJob?.cancel()
    }

    fun updateProgress() {
        _uiState.update {
            if (it.progress >= 1f) {
                openDialog("$SUMMARY_ROUTE/${it.score}")
                return
            }
            startCountdown()
            val currentProgress = it.progress
            it.copy(
                selectedOption = "",
                progress = currentProgress + 0.1f,
                currentQuiz = it.questions[(currentProgress * 10).toInt()]
            )
        }
    }

    fun endQuiz(onNavigate: (String) -> Unit) {
        closeDialog()
        onNavigate(uiState.value.destination)
    }

    fun openDialog(destination: String) {
        _uiState.update {
            it.copy(showDialog = true, destination = destination)
        }
    }

    fun closeDialog() {
        _uiState.update {
            it.copy(showDialog = false)
        }
    }

    fun onFinishAnimation() {
        _uiState.update {
            it.copy(
                animationType = Default
            )
        }
        startCountdown()
    }

    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            for (i in 30 downTo 0) {
                if (isActive) {
                    _uiState.update {
                        it.copy(
                            timerProgress = i.toFloat() / 30,
                        )
                    }
                    delay(1000)
                    if (i == 0) selectOption(uiState.value.currentQuiz?.answer!!, true)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        countdownJob = null
    }

    fun onClickAnimation(onNavigate: (String) -> Unit) {
        _uiState.let {
            val currentValue = it.value
            when (currentValue.animationType) {
                LoadingFailure -> generateQuestions(currentValue.prompt)
                Summary -> onNavigate(HOME_ROUTE)
                else -> {
                    it.update { state ->
                        state.copy(animationType = Countdown)
                    }
                }
            }
        }
    }
}
