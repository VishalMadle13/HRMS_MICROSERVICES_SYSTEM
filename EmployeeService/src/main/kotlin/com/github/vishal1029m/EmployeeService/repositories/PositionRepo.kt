package com.github.vishal1029m.EmployeeService.repositories

import com.github.vishal1029m.EmployeeService.entites.Position
import org.springframework.data.jpa.repository.JpaRepository

interface PositionRepo : JpaRepository<Position, String> {

}