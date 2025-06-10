package com.elearning.quiz.infrastructure.persistence

import com.elearning.quiz.domain.model.Question
import com.elearning.quiz.domain.model.Quiz
import com.elearning.quiz.domain.model.QuizQuestion
import com.elearning.quiz.domain.port.output.QuizRepository
import org.springframework.stereotype.Repository

@Repository
class JpaQuizRepository(
    private val springDataQuizRepository: SpringDataQuizRepository,
    private val springDataQuestionRepository: SpringDataQuestionRepository
) : QuizRepository {

    override fun findAll(): List<Quiz> =
        springDataQuizRepository.findAll().map { toDomain(it) }

    override fun findById(id: Long): Quiz? =
        springDataQuizRepository.findById(id).orElse(null)?.let { toDomain(it) }

    override fun save(quiz: Quiz): Quiz {
        val entity = toEntity(quiz)
        val saved = springDataQuizRepository.save(entity)
        return toDomain(saved)
    }

    private fun toDomain(entity: QuizEntity): Quiz {
        val questions = entity.quizQuestions.map {
            QuizQuestion(
                question = Question(
                    id = it.question.id,
                    text = it.question.text,
                    answer = it.question.answer
                ),
                score = it.score
            )
        }
        return Quiz(
            id = entity.id,
            title = entity.title,
            questions = questions,
            startTime = entity.startTime,
            endTime = entity.endTime
        )
    }

    private fun toEntity(quiz: Quiz): QuizEntity {
        val entity = if (quiz.id == null) {
            QuizEntity(
                title = quiz.title,
                startTime = quiz.startTime,
                endTime = quiz.endTime
            )
        } else {
            springDataQuizRepository.findById(quiz.id)
                .orElseThrow { RuntimeException("Quiz not found") }
                .apply {
                    title = quiz.title
                    startTime = quiz.startTime
                    endTime = quiz.endTime
                    quizQuestions.clear()
                }
        }

        quiz.questions.forEach { qq ->
            val question = if (qq.question.id != null) {
                springDataQuestionRepository.findById(qq.question.id)
                    .orElseThrow { RuntimeException("Question not found: ${qq.question.id}") }
            } else {
                QuestionEntity(text = qq.question.text, answer = qq.question.answer).also {
                    springDataQuestionRepository.save(it)
                }
            }

            val qqEntity = QuizQuestionEntity(
                quiz = entity,
                question = question,
                score = qq.score
            )
            entity.quizQuestions.add(qqEntity)
        }

        return entity
    }
}