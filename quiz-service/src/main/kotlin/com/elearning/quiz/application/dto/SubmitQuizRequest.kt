package com.elearning.quiz.application.dto

data class SubmitQuizRequest(
    val userId: String,
    val answers: Map<Long, String>
)
