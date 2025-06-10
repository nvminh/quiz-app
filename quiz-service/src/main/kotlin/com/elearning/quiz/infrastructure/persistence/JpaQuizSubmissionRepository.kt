package com.elearning.quiz.infrastructure.persistence

import com.elearning.quiz.domain.model.QuizSubmission
import com.elearning.quiz.domain.port.output.QuizSubmissionRepository
import org.springframework.stereotype.Repository

@Repository
class JpaQuizSubmissionRepository(
    private val springRepo: SpringDataQuizSubmissionRepository
) : QuizSubmissionRepository {

    override fun save(session: QuizSubmission): QuizSubmission {
        val entity = QuizSubmissionEntity(
            id = session.id,
            userId = session.userId,
            quizId = session.quizId,
            score = session.score,
            answers = session.answers
        )
        return springRepo.save(entity).toDomain()
    }

    private fun QuizSubmissionEntity.toDomain(): QuizSubmission = QuizSubmission(
        id = this.id,
        userId = this.userId,
        quizId = this.quizId,
        score = this.score,
        answers = this.answers
    )
}