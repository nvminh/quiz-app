package com.elearning.quiz.domain.port.input

import com.elearning.quiz.domain.model.Quiz
import com.elearning.quiz.domain.model.QuizSession

interface QuizService {
    fun getAll(): List<Quiz>
    fun getById(id: Long): Quiz?
    fun save(quiz: Quiz): Quiz
    fun submitAnswers(userId: String, quizId: Long, answers: Map<Long, String>): Int
    fun joinQuiz(userId: String, quizId: Long): QuizSession
}
