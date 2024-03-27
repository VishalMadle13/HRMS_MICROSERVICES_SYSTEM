package com.github.vishal1029m.AttendanceService.repositories

import com.github.vishal1029m.AttendanceService.entites.Attendance
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface AttendanceRepo : JpaRepository<Attendance, Long> {
    fun findByEmployeeIdAndDate(employeeId: Long, date: LocalDate): List<Attendance>
    fun findByEmployeeIdAndDateBetween(employeeId: Long, start: LocalDate, end: LocalDate): List<Attendance>

}
