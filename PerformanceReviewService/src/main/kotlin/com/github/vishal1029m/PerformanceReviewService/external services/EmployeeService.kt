package com.github.vishal1029m.PerformanceReviewService.`external services`

 import com.github.vishal1029m.PerformanceReviewService.config.feignconfig.FeignConfiguration
  import com.github.vishal1029m.PerformanceReviewService.payloads.EmployeeDto
 import org.springframework.cloud.openfeign.FeignClient
 import org.springframework.http.ResponseEntity
 import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Service
@FeignClient(name = "EMPLOYEE-SERVICE", configuration = [FeignConfiguration::class])
 public interface  EmployeeService {
    @GetMapping("/api/employee/")
    fun getEmployee():ResponseEntity<List<EmployeeDto>>
    @GetMapping("/api/employee/{employeeId}")
    fun getEmployeeById( @PathVariable employeeId : Long) : ResponseEntity<EmployeeDto>
}