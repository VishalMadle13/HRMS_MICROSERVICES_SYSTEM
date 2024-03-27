package com.github.vishal1029m.AttendanceService.payloads.attendance


import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.Date
import jakarta.validation.constraints.NotNull

@NoArgsConstructor
@AllArgsConstructor
@Data
data class AttendanceDto(
    var id: Long? = null,
    @field:NotNull(message = "Employee Id cannot be null")
    var employeeId: Long? = null,

    @field:NotNull(message = "Date cannot be null")
    var date: LocalDate = LocalDate.now(),

    var clockInTime: Date = Date(),
    var clockOutTime: Date = Date()
)