package com.orgustine.testtaker.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Quiz(
    val question: String,
    val optionsList: List<String>,
    val answer: String,
)
