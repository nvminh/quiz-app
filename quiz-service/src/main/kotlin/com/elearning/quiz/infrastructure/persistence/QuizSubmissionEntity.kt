package com.elearning.quiz.infrastructure.persistence
import jakarta.persistence.*

@Entity
@Table(name = "quiz_submissions")
class QuizSubmissionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: String,
    val quizId: Long,
    val score: Int,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "submission_answers", joinColumns = [JoinColumn(name = "submission_id")])
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    val answers: Map<Long, String>
)