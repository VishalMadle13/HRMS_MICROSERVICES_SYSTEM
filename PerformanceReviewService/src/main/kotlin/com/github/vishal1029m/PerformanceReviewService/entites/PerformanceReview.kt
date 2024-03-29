package com.github.vishal1029m.PerformanceReviewService.entites

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["employee_id", "reviewDate"])])
 data class PerformanceReview(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    var id: Long? = null,
    var employeeId: Long? = null,
    var reviewDate: LocalDate = LocalDate.now(),
    var rating: Float = 0f,
    var comments: String = ""
)