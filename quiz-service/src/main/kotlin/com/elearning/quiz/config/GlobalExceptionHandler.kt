package com.elearning.quiz.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorResponse("Invalid request", ex.message))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalState(ex: IllegalStateException): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(errorResponse("Invalid state", ex.message))
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntime(ex: RuntimeException): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse("Unexpected error", ex.message))
    }

    private fun errorResponse(error: String, message: String?): Map<String, Any> {
        return mapOf(
            "error" to error,
            "message" to (message ?: "No details available"),
            "timestamp" to System.currentTimeMillis()
        )
    }
}