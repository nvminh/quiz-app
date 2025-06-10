package com.elearning.quiz.application.controller

import com.elearning.quiz.application.dto.*
import com.elearning.quiz.domain.model.Question
import com.elearning.quiz.domain.model.Quiz
import com.elearning.quiz.domain.model.QuizQuestion
import com.elearning.quiz.domain.model.QuizSession
import com.elearning.quiz.domain.port.input.QuizService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quizzes")
class QuizController(
    private val quizService: QuizService
) {

    @GetMapping
    fun getAll(): List<QuizResponse> =
        quizService.getAll().map { quiz ->
            QuizResponse(
                id = quiz.id ?: 0,
                title = quiz.title,
                startTime = quiz.startTime,
                endTime = quiz.endTime,
                questions = quiz.questions.map { qq ->
                    QuestionResponse(
                        id = qq.question.id ?: 0,
                        text = qq.question.text,
                        score = qq.score
                    )
                }
            )
        }

    @GetMapping("/{quizId}")
    fun getById(@PathVariable quizId: Long): ResponseEntity<QuizResponse> {
        val quiz = quizService.getById(quizId) ?: return ResponseEntity.notFound().build()
        val response = QuizResponse(
            id = quiz.id ?: 0,
            title = quiz.title,
            startTime = quiz.startTime,
            endTime = quiz.endTime,
            questions = quiz.questions.map { qq ->
                QuestionResponse(
                    id = qq.question.id ?: 0,
                    text = qq.question.text,
                    score = qq.score
                )
            }
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun createQuiz(@RequestBody request: CreateQuizRequest): QuizResponse {
        val quiz = Quiz(
            id = null,
            title = request.title,
            startTime = request.startTime,
            endTime = request.endTime,
            questions = request.questions.map { q ->
                QuizQuestion(
                    question = Question(
                        id = q.id,
                        text = q.text,
                        answer = q.answer
                    ),
                    score = q.score
                )
            }
        )
        val saved = quizService.save(quiz)
        return QuizResponse(
            id = saved.id ?: 0,
            title = saved.title,
            startTime = saved.startTime,
            endTime = saved.endTime,
            questions = saved.questions.map { qq ->
                QuestionResponse(
                    id = qq.question.id ?: 0,
                    text = qq.question.text,
                    score = qq.score
                )
            }
        )
    }

    @PostMapping("/{quizId}/results")
    fun submitQuiz(@PathVariable quizId: Long, @RequestBody request: SubmitQuizRequest): SubmitQuizResponse {
        val score = quizService.submitAnswers(request.userId, quizId, request.answers)
        return SubmitQuizResponse(request.userId, quizId, score)
    }

    @PostMapping("/{quizId}/join")
    fun joinQuiz(@PathVariable quizId: Long, @RequestBody request: JoinQuizRequest): QuizSession {
        return quizService.joinQuiz(request.userId, quizId)
    }
}
