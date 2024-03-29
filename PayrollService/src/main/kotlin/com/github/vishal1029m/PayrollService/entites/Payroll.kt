package com.github.vishal1029m.PayrollService.entites

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date

@Entity
data class Payroll(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,
    var employeeId: Long? = null,
    var month: Int? = null,
    var year: Int? = null,
    var basicSalary: Long? = null,
    var deduction: Long? = null,
    var netSalary: Long? = null
)