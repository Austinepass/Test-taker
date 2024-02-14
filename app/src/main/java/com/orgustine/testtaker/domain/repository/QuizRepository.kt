package com.orgustine.testtaker.domain.repository

import com.orgustine.testtaker.data.remote.QuizRequestBody

interface QuizRepository {
    suspend fun getQuestions(request: QuizRequestBody)
}
