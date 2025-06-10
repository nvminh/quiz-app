package com.elearning.quiz.application.service

import com.elearning.quiz.application.kafka.QuizScoreMessage
import com.elearning.quiz.application.kafka.QuizScoreProducer
import com.elearning.quiz.domain.model.Quiz
import com.elearning.quiz.domain.model.QuizSession
import com.elearning.quiz.domain.model.QuizSubmission
import com.elearning.quiz.domain.port.input.QuizService
import com.elearning.quiz.domain.port.output.QuizRepository
import com.elearning.quiz.domain.port.output.QuizSessionRepository
import com.elearning.quiz.domain.port.output.QuizSubmissionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class QuizServiceImpl(
    private val quizRepository: QuizRepository,
    private val quizSessionRepository: QuizSessionRepository,
    private val quizSubmissionRepository: QuizSubmissionRepository,
    private val quizScoreProducer: QuizScoreProducer
) : QuizService {

    override fun getAll(): List<Quiz> = quizRepository.findAll()

    override fun getById(id: Long): Quiz? = quizRepository.findById(id)

    override fun save(quiz: Quiz): Quiz = quizRepository.save(quiz)

    override fun submitAnswers(userId: String, quizId: Long, answers: Map<Long, String>): Int {
        val quiz = quizRepository.findById(quizId) ?: throw IllegalArgumentException("Quiz not found")
        val now = LocalDateTime.now()
        if(now.isBefore(quiz.startTime) || now.isAfter(quiz.endTime)) {
            throw IllegalArgumentException("Invalid submission time")
        }

        quizSessionRepository.findByUserIdAndQuizId(userId, quizId)
            ?: throw IllegalArgumentException("User didn't join the quiz")

        var totalScore = 0
        for (qq in quiz.questions) {
            val userAnswer = answers[qq.question.id]
            if (userAnswer != null && userAnswer.trim().equals(qq.question.answer.trim(), ignoreCase = true)) {
                totalScore += qq.score
            }
        }

        // Save submission
        quizSubmissionRepository.save(
            QuizSubmission(
                userId = userId,
                quizId = quizId,
                answers = answers,
                score = totalScore
            )
        )

        // Send to Kafka
        quizScoreProducer.send(
            QuizScoreMessage(userId = userId, quizId = quizId, score = totalScore)
        )

        return totalScore
    }

    override fun joinQuiz(userId: String, quizId: Long): QuizSession {
        quizRepository.findById(quizId) ?: throw RuntimeException("Quiz not found")
        val existing = quizSessionRepository.findByUserIdAndQuizId(userId, quizId)
        return existing ?: quizSessionRepository.save(QuizSession(userId = userId, quizId = quizId))
    }
}
