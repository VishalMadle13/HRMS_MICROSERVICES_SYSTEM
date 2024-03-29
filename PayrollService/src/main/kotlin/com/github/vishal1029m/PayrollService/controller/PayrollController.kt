package com.github.vishal1029m.PayrollService.controller

import com.github.vishal1029m.PayrollService.payloads.payroll.PayrollDto
import com.github.vishal1029m.PayrollService.services.PayrollService
import com.github.vishal1029m.PayrollService.utils.ApiResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payroll")
class PayrollController(
    @Autowired val payrollService: PayrollService
) {
    // ----------------------------- Get All Payroll ----------------------------
    @GetMapping("/")
    fun getAllPayrolls(): ResponseEntity<List<PayrollDto>> {
        return ResponseEntity(
            this.payrollService.getAllPayrolls(), HttpStatus.OK
        )
    }

    // -------------------------------- Get Payroll By Id -------------------------------
    @GetMapping("/{payrollId}")
    fun getPayrollById(@PathVariable("payrollId") payrollId: Long): ResponseEntity<PayrollDto> {
        return ResponseEntity(
            this.payrollService.getPayrollById(payrollId), HttpStatus.OK
        )
    }

    // -------------------------------- Get Payroll of Employee  -------------------------------
//    @GetMapping("/employee/{employeeId}/month")
//    fun getPayrollOfEmployeeByYearAndMonth(
//        @PathVariable("employeeId") employeeId: Long,
//        @RequestParam year: Int,
//        @RequestParam month: Int
//    ): ResponseEntity<Any> {
//        return ResponseEntity(
//            this.payrollService.getPayrollByEmployeeAndMonth(employeeId, year, month), HttpStatus.OK
//        )
//    }

    // -------------------------------- Add Payroll --------------------------------
    @PostMapping("/")
    fun addPayroll(@RequestBody @Valid PayrollDto: PayrollDto): ResponseEntity<PayrollDto> {
        return ResponseEntity(
            this.payrollService.addPayroll(PayrollDto), HttpStatus.CREATED
        )
    }

    // -------------------------------- Update Payroll --------------------------------
    @PutMapping("/{payrollId}")
    fun updatePayroll(
        @PathVariable("payrollId") payrollId: Long,
        @Valid @RequestBody PayrollDto: PayrollDto
    ): ResponseEntity<PayrollDto> {
        if (payrollId != PayrollDto.id) {
            val failResponse: MultiValueMap<String, String> = LinkedMultiValueMap()
            failResponse.add("message", "Request-Body and Path-Variable is NOT Equal")
            failResponse.add("success", "false")
            return ResponseEntity(PayrollDto(), failResponse, HttpStatus.NOT_MODIFIED)
        }
        return ResponseEntity(
            this.payrollService.updatePayroll(payrollId, PayrollDto), HttpStatus.OK
        )
    }

    // -------------------------------- Delete Payroll --------------------------------
    @DeleteMapping("/{payrollId}")
    fun deletePayrollById(@PathVariable("payrollId") payrollId: Long): ResponseEntity<ApiResponse> {
        this.payrollService.deletePayroll(payrollId)
        return ResponseEntity(
            ApiResponse("Payroll Deleted Successfully", true), HttpStatus.OK
        )
    }
}

