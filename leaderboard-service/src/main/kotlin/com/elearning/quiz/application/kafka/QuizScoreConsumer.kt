package com.elearning.quiz.application.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class QuizScoreConsumer(
    val scores: MutableMap<Long, MutableMap<String, QuizScoreMessage>> = HashMap<Long, MutableMap<String, QuizScoreMessage>>().toMutableMap()) {

    private val logger = LoggerFactory.getLogger(QuizScoreConsumer::class.java)

    @KafkaListener(topics = ["score_event_by_quiz"], groupId = "quiz-score-consumer-group")
    fun consume(message: QuizScoreMessage) {
        logger.info("🎯 Received quiz score: userId=${message.userId}, quizId=${message.quizId}, score=${message.score}")

        var quizScores = scores[message.quizId]
        if(quizScores == null) {
            quizScores = HashMap()
            scores[message.quizId] = quizScores
        }
        quizScores[message.userId] = message
    }
}