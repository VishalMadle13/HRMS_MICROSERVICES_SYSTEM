package com.github.vishal1029m.PayrollService.repositories

import com.github.vishal1029m.PayrollService.entites.Payroll
import com.github.vishal1029m.PayrollService.payloads.EmployeeDto
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface PayrollRepo : JpaRepository<Payroll, Long> {
    fun findByEmployeeIdAndYearAndMonth(employeeId: Long, year: Int, month: Int): List<Payroll>
}
