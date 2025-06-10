package com.elearning.quiz.domain.model

class QuizSubmission(
    val id: Long? = null,
    val userId: String,
    val quizId: Long,
    val score: Int,
    val answers: Map<Long, String>
)