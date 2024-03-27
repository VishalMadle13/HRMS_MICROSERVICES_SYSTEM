package com.github.vishal1029m.AttendanceService.payloads.attendance

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.*

@NoArgsConstructor
@AllArgsConstructor
data class MonthlyAttendanceDto(
    var date: LocalDate,
    var clockInTime: Date,
    var clockOutTime: Date
)