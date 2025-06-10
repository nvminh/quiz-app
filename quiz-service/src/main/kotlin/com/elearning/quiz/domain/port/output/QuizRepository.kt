package com.elearning.quiz.domain.port.output

import com.elearning.quiz.domain.model.Quiz

interface QuizRepository {
    fun findAll(): List<Quiz>
    fun findById(id: Long): Quiz?
    fun save(quiz: Quiz): Quiz
}
