package com.elearning.quiz.application.dto

data class SubmitQuizResponse(
    val userId: String,
    val quizId: Long,
    val totalScore: Int
)
