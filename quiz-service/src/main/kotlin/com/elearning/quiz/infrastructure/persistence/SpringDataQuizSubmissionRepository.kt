package com.elearning.quiz.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataQuizSubmissionRepository : JpaRepository<QuizSubmissionEntity, Long>
