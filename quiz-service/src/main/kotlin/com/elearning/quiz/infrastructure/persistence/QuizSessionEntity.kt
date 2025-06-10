package com.elearning.quiz.infrastructure.persistence

import jakarta.persistence.*

@Entity
@Table(name = "quiz_sessions")
data class QuizSessionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: String,
    val quizId: Long
)