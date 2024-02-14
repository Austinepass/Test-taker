package com.orgustine.testtaker.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface QuizApi {

    @GET("/questions")
    suspend fun getQuestions() : ResponseBody
}