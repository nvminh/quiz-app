package com.elearning.quiz.application.dto

data class QuestionResponse(
    val id: Long,
    val text: String,
    val score: Int
)
