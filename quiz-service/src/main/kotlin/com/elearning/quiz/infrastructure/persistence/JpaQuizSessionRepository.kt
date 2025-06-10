package com.elearning.quiz.infrastructure.persistence

import com.elearning.quiz.domain.model.QuizSession
import com.elearning.quiz.domain.port.output.QuizSessionRepository
import org.springframework.stereotype.Repository

@Repository
class JpaQuizSessionRepository(
    private val springRepo: SpringDataQuizSessionRepository
) : QuizSessionRepository {

    override fun save(session: QuizSession): QuizSession {
        val entity = QuizSessionEntity(
            id = session.id,
            userId = session.userId,
            quizId = session.quizId
        )
        return springRepo.save(entity).toDomain()
    }

    override fun findByUserIdAndQuizId(userId: String, quizId: Long): QuizSession? {
        return springRepo.findByUserIdAndQuizId(userId, quizId)?.toDomain()
    }

    private fun QuizSessionEntity.toDomain(): QuizSession = QuizSession(
        id = this.id,
        userId = this.userId,
        quizId = this.quizId
    )
}