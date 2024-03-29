package com.github.vishal1029m.PayrollService.services

import com.github.vishal1029m.PayrollService.payloads.payroll.PayrollDto
import org.springframework.stereotype.Service

@Service
interface PayrollService {
    fun getAllPayrolls(): List<PayrollDto>
    fun getPayrollById(payrollId: Long): PayrollDto?
    fun addPayroll(payrollDto: PayrollDto): PayrollDto
    fun updatePayroll(payrollId: Long, payrollDto: PayrollDto): PayrollDto?
    fun deletePayroll(payrollId: Long)
}