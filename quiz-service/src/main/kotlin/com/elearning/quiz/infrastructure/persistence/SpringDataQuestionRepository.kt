package com.elearning.quiz.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataQuestionRepository : JpaRepository<QuestionEntity, Long>
