package com.elearning.quiz.application.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class QuizScoreProducer(
    private val kafkaTemplate: KafkaTemplate<String, QuizScoreMessage>
) {
    private val topic = "score_event_by_quiz"

    fun send(scoreMessage: QuizScoreMessage) {
        kafkaTemplate.send(topic, scoreMessage.userId, scoreMessage)
    }
}