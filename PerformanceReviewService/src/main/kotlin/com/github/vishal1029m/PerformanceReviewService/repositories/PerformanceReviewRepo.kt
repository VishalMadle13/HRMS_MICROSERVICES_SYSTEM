package com.github.vishal1029m.PerformanceReviewService.repositories

import com.github.vishal1029m.PerformanceReviewService.entites.PerformanceReview
import com.github.vishal1029m.PerformanceReviewService.payloads.EmployeeDto
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface PerformanceReviewRepo : JpaRepository<PerformanceReview, Long> {
    fun findByEmployeeId(employeeId: Long): List<PerformanceReview>
    fun findByEmployeeIdAndReviewDateBetween(
        employeeId: Long,
        start: LocalDate,
        end: LocalDate
    ): List<PerformanceReview>

}
