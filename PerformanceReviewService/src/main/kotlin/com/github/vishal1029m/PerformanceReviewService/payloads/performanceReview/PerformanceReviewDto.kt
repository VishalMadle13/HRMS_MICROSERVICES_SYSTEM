package com.github.vishal1029m.PerformanceReviewService.payloads.performanceReview


import jakarta.validation.constraints.NotBlank
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.Date
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.PositiveOrZero

@NoArgsConstructor
@AllArgsConstructor
@Data
data class PerformanceReviewDto(
    var id: Long? = null,

    @field:NotNull(message = "Employee ID cannot be null")
    var employeeId: Long? = null,

    @field:PastOrPresent(message = "Review date must be in the past or present")
    var reviewDate: LocalDate = LocalDate.now(),

    @field:PositiveOrZero(message = "Rating must be a positive number or zero")
    var rating: Float = 0f,

    @field:NotBlank(message = "Comments cannot be blank")
    var comments: String = ""
)