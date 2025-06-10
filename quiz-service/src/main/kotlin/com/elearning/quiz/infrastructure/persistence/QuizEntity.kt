package com.elearning.quiz.infrastructure.persistence

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "quizzes")
data class QuizEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,

    var startTime: LocalDateTime,
    var endTime: LocalDateTime,

    @OneToMany(mappedBy = "quiz", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val quizQuestions: MutableList<QuizQuestionEntity> = mutableListOf()
)