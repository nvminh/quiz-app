package com.elearning.quiz.domain.port.output


import com.elearning.quiz.domain.model.QuizSession

interface QuizSessionRepository {
    fun save(session: QuizSession): QuizSession
    fun findByUserIdAndQuizId(userId: String, quizId: Long): QuizSession?
}