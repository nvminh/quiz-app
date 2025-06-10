package com.elearning.quiz.domain.port.output

import com.elearning.quiz.domain.model.Question

interface QuestionRepository {
    fun findById(id: Long): Question?
    fun save(question: Question): Question
}
