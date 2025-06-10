package com.elearning.quiz.application.dto

import java.time.LocalDateTime

data class CreateQuizRequest(
    val title: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val questions: List<CreateQuizQuestionRequest>
)
