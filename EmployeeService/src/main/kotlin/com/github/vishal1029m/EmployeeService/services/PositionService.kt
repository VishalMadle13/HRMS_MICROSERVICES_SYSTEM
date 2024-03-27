package com.github.vishal1029m.EmployeeService.services

import com.github.vishal1029m.EmployeeService.payloads.PositionDto
import org.springframework.stereotype.Service

@Service
interface PositionService {
    fun getAllPositions(): List<PositionDto>
    fun getPositionById(positionId: String): PositionDto?
    fun addPosition(positionDto: PositionDto): PositionDto
    fun updatePosition(positionId: String, positionDto: PositionDto): PositionDto?
    fun deletePosition(positionId: String)
}