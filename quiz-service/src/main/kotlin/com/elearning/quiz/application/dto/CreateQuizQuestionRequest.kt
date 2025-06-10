package com.elearning.quiz.application.dto

data class CreateQuizQuestionRequest(
    val id: Long?, // Optional if new
    val text: String,
    val answer: String,
    val score: Int
)