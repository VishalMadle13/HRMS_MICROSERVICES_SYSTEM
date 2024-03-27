package com.github.vishal1029m.EmployeeService.repositories

import com.github.vishal1029m.EmployeeService.entites.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepo : JpaRepository<Employee, Long> {
}