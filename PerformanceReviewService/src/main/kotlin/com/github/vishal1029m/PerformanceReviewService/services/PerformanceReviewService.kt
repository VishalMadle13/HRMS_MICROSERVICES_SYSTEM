package com.github.vishal1029m.PerformanceReviewService.services

import com.github.vishal1029m.PerformanceReviewService.payloads.performanceReview.PerformanceReviewDto
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
interface PerformanceReviewService {
    fun getAllPerformanceReview(): List<PerformanceReviewDto>
    fun getPerformanceReviewById(performanceReviewId: Long): PerformanceReviewDto?
    fun getPerformanceReviewByEmployee(employeeId: Long): List<PerformanceReviewDto>
    fun addPerformanceReview(performanceReviewDto: PerformanceReviewDto): PerformanceReviewDto
    fun updatePerformanceReview(
        performanceReviewId: Long,
        performanceReviewDto: PerformanceReviewDto
    ): PerformanceReviewDto

    fun deletePerformanceReview(performanceReviewId: Long)
    fun getPerformanceReviewByEmployeeBetweenDate(
        employeeId: Long,
        startDate: LocalDate?,
        endDate: LocalDate?
    ): List<PerformanceReviewDto>
}