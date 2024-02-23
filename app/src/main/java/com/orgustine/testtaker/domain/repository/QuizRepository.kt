package com.orgustine.testtaker.domain.repository

import com.orgustine.testtaker.data.remote.QuizResponse
import com.orgustine.testtaker.util.Resource

interface QuizRepository {
    suspend fun getQuestions(prompt: String) : Resource<QuizResponse>
}
