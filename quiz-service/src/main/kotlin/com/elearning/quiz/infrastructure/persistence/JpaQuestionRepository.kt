package com.elearning.quiz.infrastructure.persistence

import com.elearning.quiz.domain.model.Question
import com.elearning.quiz.domain.port.output.QuestionRepository
import org.springframework.stereotype.Repository

@Repository
class JpaQuestionRepository(
    private val springDataQuestionRepository: SpringDataQuestionRepository
) : QuestionRepository {

    override fun findById(id: Long): Question? =
        springDataQuestionRepository.findById(id).orElse(null)?.let {
            Question(it.id, it.text, it.answer)
        }

    override fun save(question: Question): Question {
        val entity = QuestionEntity(question.id, question.text, question.answer)
        val saved = springDataQuestionRepository.save(entity)
        return Question(saved.id, saved.text, saved.answer)
    }
}
