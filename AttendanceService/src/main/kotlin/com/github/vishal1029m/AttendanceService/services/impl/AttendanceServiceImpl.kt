package com.github.vishal1029m.AttendanceService.services.impl


import com.github.vishal1029m.AttendanceService.entites.Attendance
import com.github.vishal1029m.AttendanceService.exceptions.ResourceAlreadyExistException
import com.github.vishal1029m.AttendanceService.exceptions.ResourceNotFoundException
import com.github.vishal1029m.AttendanceService.`external services`.EmployeeService
import com.github.vishal1029m.AttendanceService.payloads.EmployeeDto
import com.github.vishal1029m.AttendanceService.payloads.attendance.AttendanceDto
import com.github.vishal1029m.AttendanceService.payloads.attendance.MonthlyAttendanceDto
import com.github.vishal1029m.AttendanceService.repositories.AttendanceRepo
import com.github.vishal1029m.AttendanceService.services.AttendanceService
 import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.stream.Collectors

@Component
class AttendanceServiceImpl(
    @Autowired private val attendanceRepo: AttendanceRepo,
    @Autowired private val mapper: ModelMapper,
    @Autowired private val employeeService : EmployeeService
 ) : AttendanceService {
    override fun getAllAttendance(): List<AttendanceDto> {
//        println(
//            employeeService.getEmployee(100204)
//        )
        return attendanceRepo
            .findAll()
            .stream()
            .map { attendance: Attendance ->
                mapper.map(attendance, AttendanceDto::class.java)
            }
            .collect(Collectors.toList())
    }

    override fun getAttendanceById(attendanceId: Long): AttendanceDto? {
        val positionEntity: Attendance = attendanceRepo
            .findById(attendanceId)
            .orElseThrow {
                ResourceNotFoundException("Attendance", "Id", attendanceId)
            }
        return mapper.map(
            positionEntity, AttendanceDto::class.java
        )
    }

    override fun addAttendance(attendanceDto: AttendanceDto): AttendanceDto {
        println(attendanceDto)
        val employee: EmployeeDto = employeeService.getEmployeeById(attendanceDto.employeeId!!).body as EmployeeDto

        if (attendanceRepo.findByEmployeeIdAndDate(attendanceDto.employeeId!!, attendanceDto.date).isNotEmpty()) {
            throw ResourceAlreadyExistException("Attendance")
        }
        val attendance: Attendance = mapper.map(attendanceDto, Attendance::class.java)

        return mapper.map(
            attendanceRepo.save(attendance), AttendanceDto::class.java
        )
    }

    override fun updateAttendance(attendanceId: Long, attendanceDto: AttendanceDto): AttendanceDto {
        attendanceRepo
            .findById(attendanceId)
            .orElseThrow {
                ResourceNotFoundException("Attendance", "Id", attendanceId)
            }
        val employee: EmployeeDto = employeeService.getEmployeeById(attendanceDto.employeeId!!).body as EmployeeDto
        val attendance: Attendance = mapper.map(attendanceDto, Attendance::class.java)

        return mapper.map(
            attendanceRepo.save(attendance), AttendanceDto::class.java
        )
    }

    override fun deleteAttendance(attendanceId: Long) {
        attendanceRepo
            .findById(attendanceId)
            .orElseThrow {
                ResourceNotFoundException("Attendance", "Id", attendanceId)
            }
        attendanceRepo.deleteById(attendanceId)
    }

    override fun getAttendanceByEmployeeAndMonth(employeeId: Long, year: Int, month: Int): Any {
        val startOfMonth = LocalDate.of(year, month, 1)
        val endOfMonth = startOfMonth.plusMonths(1).minusDays(1)
        val response: ResponseEntity<Any> = employeeService.getEmployeeById(employeeId);
        println(response.statusCode)
        println(response.body)
        val employee : EmployeeDto =  response.body  as EmployeeDto
        val attendanceRecords: List<Attendance> =
            attendanceRepo
                .findByEmployeeIdAndDateBetween(
                    employeeId, startOfMonth, endOfMonth
                )
        val monthlyAttendanceDtos: MutableList<MonthlyAttendanceDto> = ArrayList()
        for (it in attendanceRecords) {
            monthlyAttendanceDtos.add(
                    MonthlyAttendanceDto(it.date, it.clockInTime, it.clockOutTime)
                )
        }

        return object {
            val employeeId: Long = employeeId
            val firstName: String = employee.firstName
            val lastName: String = employee.lastName
            val attendedDays: Int = monthlyAttendanceDtos.size
            val attendance: List<MonthlyAttendanceDto> = monthlyAttendanceDtos
        }
    }
}

