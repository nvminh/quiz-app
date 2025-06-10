package com.elearning.quiz.domain.model

import java.time.LocalDateTime

data class Quiz(
    val id: Long?,
    val title: String,
    val questions: List<QuizQuestion> = emptyList(),
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)