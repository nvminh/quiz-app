package com.elearning.quiz.application.controller

import com.elearning.quiz.application.kafka.QuizScoreConsumer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/leaderboard")
class LeaderboardController(
    private val quizScoreConsumer: QuizScoreConsumer
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val board = quizScoreConsumer.scores[id]?.values?.sortedByDescending { it.score }
        return ResponseEntity.ok(board)
    }
}