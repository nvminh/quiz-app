package com.elearning.quiz.application.dto

import java.time.LocalDateTime

data class QuizResponse(
    val id: Long,
    val title: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val questions: List<QuestionResponse>
)