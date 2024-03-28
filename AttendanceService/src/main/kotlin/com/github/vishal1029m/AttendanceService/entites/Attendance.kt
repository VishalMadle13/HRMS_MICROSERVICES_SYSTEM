package com.github.vishal1029m.AttendanceService.entites

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["employee_id", "date"])])
data class Attendance(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var employeeId: Long? = null,
    var date: LocalDate = LocalDate.now(),
    var clockInTime: Date = Date(),
    var clockOutTime: Date = Date(),
)