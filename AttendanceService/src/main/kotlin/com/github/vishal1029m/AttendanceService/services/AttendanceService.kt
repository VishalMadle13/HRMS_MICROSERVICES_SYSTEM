package com.github.vishal1029m.AttendanceService.services

import com.github.vishal1029m.AttendanceService.payloads.attendance.AttendanceDto
import org.springframework.stereotype.Service

@Service
interface AttendanceService {
    fun getAllAttendance(): List<AttendanceDto>
    fun getAttendanceById(attendanceId: Long): AttendanceDto?
    fun addAttendance(attendanceDto: AttendanceDto): AttendanceDto
    fun updateAttendance(attendanceId: Long, attendanceDto: AttendanceDto): AttendanceDto
    fun deleteAttendance(attendanceId: Long)
    fun getAttendanceByEmployeeAndMonth(employeeId: Long, year: Int, month: Int): Any
}