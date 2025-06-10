package com.elearning.quiz.infrastructure.persistence

import jakarta.persistence.*

@Entity
@Table(name = "quiz_questions")
data class QuizQuestionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    val quiz: QuizEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    val question: QuestionEntity,

    val score: Int
)
