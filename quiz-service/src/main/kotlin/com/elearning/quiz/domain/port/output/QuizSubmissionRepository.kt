package com.elearning.quiz.domain.port.output


import com.elearning.quiz.domain.model.QuizSubmission

interface QuizSubmissionRepository {
    fun save(session: QuizSubmission): QuizSubmission
}