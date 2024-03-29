package com.github.vishal1029m.PayrollService

import com.github.vishal1029m.PayrollService.`external services`.EmployeeService
import com.github.vishal1029m.PayrollService.payloads.EmployeeDto
import feign.FeignException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import kotlin.jvm.Throws

@SpringBootTest
class PayrollServiceApplicationTests(@Autowired val employeeService: EmployeeService) {
	@Test
	fun test(){
		println("all right")
	}
	@Test
	@Throws(FeignException::class)
	fun get() {
		val res : ResponseEntity<List<EmployeeDto>> = employeeService.getEmployee()
		println(res.statusCode)
		println(res.body)
		val res2 : ResponseEntity<EmployeeDto> = employeeService.getEmployeeById(100201)
	}
}
