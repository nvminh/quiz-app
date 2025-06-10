package com.elearning.quiz.infrastructure.persistence

import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class QuestionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val text: String,
    val answer: String
)
