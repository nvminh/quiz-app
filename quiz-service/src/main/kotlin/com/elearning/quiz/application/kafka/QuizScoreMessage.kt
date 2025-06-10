package com.elearning.quiz.application.kafka

data class QuizScoreMessage(
    val userId: String,
    val quizId: Long,
    val score: Int
)