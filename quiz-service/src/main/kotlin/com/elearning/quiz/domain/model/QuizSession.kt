package com.elearning.quiz.domain.model

data class QuizSession(
    val id: Long? = null,
    val userId: String,
    val quizId: Long
)