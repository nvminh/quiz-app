package com.elearning.quiz.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataQuizSessionRepository : JpaRepository<QuizSessionEntity, Long> {
    fun findByUserIdAndQuizId(userId: String, quizId: Long): QuizSessionEntity?
}