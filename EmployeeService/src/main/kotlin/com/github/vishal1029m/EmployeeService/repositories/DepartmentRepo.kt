package com.github.vishal1029m.EmployeeService.repositories

import com.github.vishal1029m.EmployeeService.entites.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepo : JpaRepository<Department, String> {
}