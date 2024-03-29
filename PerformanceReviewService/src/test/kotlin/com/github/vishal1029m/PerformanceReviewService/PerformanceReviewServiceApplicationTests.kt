package com.github.vishal1029m.PerformanceReviewService

import com.github.vishal1029m.PerformanceReviewService.`external services`.EmployeeService
import com.github.vishal1029m.PerformanceReviewService.payloads.EmployeeDto
import feign.FeignException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import kotlin.jvm.Throws

@SpringBootTest
class PerformanceReviewServiceApplicationTests(@Autowired val employeeService: EmployeeService) {
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
