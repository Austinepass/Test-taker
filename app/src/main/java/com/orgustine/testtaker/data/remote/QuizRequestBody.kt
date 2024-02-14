package com.orgustine.testtaker.data.remote

data class QuizRequestBody(
    val category: String,
    val difficulty: String,
    val limit: Int
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

enum class Category {
    Linux, Devops, Networking, Programming, Cloud, Docker, Kubernetes
}
