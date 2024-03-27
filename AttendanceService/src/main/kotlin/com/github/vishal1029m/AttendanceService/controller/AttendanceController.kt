package com.github.vishal1029m.AttendanceService.controller

import com.github.vishal1029m.AttendanceService.payloads.attendance.AttendanceDto
import com.github.vishal1029m.AttendanceService.services.AttendanceService
import com.github.vishal1029m.AttendanceService.utils.ApiResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/attendance")
class AttendanceController(
    @Autowired val attendanceService: AttendanceService
) {
    // ----------------------------- Get All Attendance ----------------------------
    @GetMapping("/")
    fun getAllAttendances(): ResponseEntity<List<AttendanceDto>> {
        return ResponseEntity(
            this.attendanceService.getAllAttendance(), HttpStatus.OK
        )
    }

    // -------------------------------- Get Attendance By Id -------------------------------
    @GetMapping("/{attendanceId}")
    fun getAttendanceById(@PathVariable("attendanceId") attendanceId: Long): ResponseEntity<AttendanceDto> {
        return ResponseEntity(
            this.attendanceService.getAttendanceById(attendanceId), HttpStatus.OK
        )
    }

    // -------------------------------- Get Attendance of Employee  -------------------------------
    @GetMapping("/employee/{employeeId}/month")
    fun getAttendanceOfEmployeeByYearAndMonth(
        @PathVariable("employeeId") employeeId: Long,
        @RequestParam year: Int,
        @RequestParam month: Int
    ): ResponseEntity<Any> {
        return ResponseEntity(
            this.attendanceService.getAttendanceByEmployeeAndMonth(employeeId, year, month), HttpStatus.OK
        )
    }

    // -------------------------------- Add Attendance --------------------------------
    @PostMapping("/")
    fun addAttendance(@RequestBody @Valid attendanceDto: AttendanceDto): ResponseEntity<AttendanceDto> {
        return ResponseEntity(
            this.attendanceService.addAttendance(attendanceDto), HttpStatus.CREATED
        )
    }

    // -------------------------------- Update Attendance --------------------------------
    @PutMapping("/{attendanceId}")
    fun updateAttendance(
        @PathVariable("attendanceId") attendanceId: Long,
        @Valid @RequestBody attendanceDto: AttendanceDto
    ): ResponseEntity<AttendanceDto> {
        if (attendanceId != attendanceDto.id) {
            val failResponse: MultiValueMap<String, String> = LinkedMultiValueMap()
            failResponse.add("message", "Request-Body and Path-Variable is NOT Equal")
            failResponse.add("success", "false")
            return ResponseEntity(AttendanceDto(), failResponse, HttpStatus.NOT_MODIFIED)
        }
        return ResponseEntity(
            this.attendanceService.updateAttendance(attendanceId, attendanceDto), HttpStatus.OK
        )
    }

    // -------------------------------- Delete Attendance --------------------------------
    @DeleteMapping("/{attendanceId}")
    fun deleteAttendanceById(@PathVariable("attendanceId") attendanceId: Long): ResponseEntity<ApiResponse> {
        this.attendanceService.deleteAttendance(attendanceId)
        return ResponseEntity(
            ApiResponse("Attendance Deleted Successfully", true), HttpStatus.OK
        )
    }
}

