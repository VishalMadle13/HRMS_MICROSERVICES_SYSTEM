package com.github.vishal1029m.PayrollService.services.impl


import com.github.vishal1029m.PayrollService.entites.Payroll
import com.github.vishal1029m.PayrollService.exceptions.ResourceAlreadyExistException
import com.github.vishal1029m.PayrollService.exceptions.ResourceNotFoundException
import com.github.vishal1029m.PayrollService.`external services`.EmployeeService
import com.github.vishal1029m.PayrollService.payloads.EmployeeDto
import com.github.vishal1029m.PayrollService.payloads.payroll.PayrollDto
import com.github.vishal1029m.PayrollService.repositories.PayrollRepo
import com.github.vishal1029m.PayrollService.services.PayrollService
 import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class PayrollServiceImpl(
    @Autowired val payrollRepo: PayrollRepo,
    @Autowired val mapper: ModelMapper,
    @Autowired val employeeService: EmployeeService
) : PayrollService {
    override fun getAllPayrolls(): List<PayrollDto> {
        return payrollRepo
            .findAll()
            .stream()
            .map { payroll: Payroll ->
                this.mapper.map(payroll, PayrollDto::class.java)
            }
            .collect(Collectors.toList())
    }

    override fun getPayrollById(payrollId: Long): PayrollDto? {
        val payrollEntity: Payroll = payrollRepo
            .findById(payrollId)
            .orElseThrow {
                ResourceNotFoundException("Payroll", "Id", payrollId)
            }
        return mapper.map(
            payrollEntity, PayrollDto::class.java
        )
    }

    override fun addPayroll(payrollDto: PayrollDto): PayrollDto {
        val employee: EmployeeDto = employeeService.getEmployeeById(payrollDto.employeeId!!).body!!
        if (payrollDto.id != null) {
            if (payrollRepo.findById(payrollDto.id!!).isPresent) throw ResourceAlreadyExistException("Position")
        }
        val payroll: Payroll = mapper.map(payrollDto, Payroll::class.java)
        val savedPayroll: PayrollDto = mapper.map(
            payrollRepo.save(payroll), PayrollDto::class.java
        )
        return savedPayroll
    }

    override fun updatePayroll(payrollId: Long, payrollDto: PayrollDto): PayrollDto {
        payrollRepo
            .findById(payrollId)
            .orElseThrow {
                ResourceNotFoundException("Payroll", "Id", payrollId)
            }
        val employee: EmployeeDto = employeeService.getEmployeeById(payrollDto.employeeId!!).body!!
        val updatedPayroll: Payroll = mapper.map(payrollDto, Payroll::class.java)
        updatedPayroll.id = payrollId

        return mapper.map(
            payrollRepo.save(updatedPayroll), PayrollDto::class.java
        )
    }

    override fun deletePayroll(payrollId: Long) {
        payrollRepo
            .findById(payrollId)
            .orElseThrow {
                ResourceNotFoundException("Payroll", "Id", payrollId)
            }
        payrollRepo.deleteById(payrollId)
    }
}