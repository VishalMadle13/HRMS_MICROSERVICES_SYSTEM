package com.github.vishal1029m.EmployeeService.services.impl

import com.github.vishal1029m.EmployeeService.entites.Department
import com.github.vishal1029m.EmployeeService.entites.Employee
import com.github.vishal1029m.EmployeeService.entites.Position
import com.github.vishal1029m.EmployeeService.exceptions.ResourceAlreadyExistException
import com.github.vishal1029m.EmployeeService.exceptions.ResourceNotFoundException
import com.github.vishal1029m.EmployeeService.payloads.EmployeeDto
import com.github.vishal1029m.EmployeeService.repositories.DepartmentRepo
import com.github.vishal1029m.EmployeeService.repositories.EmployeeRepo
import com.github.vishal1029m.EmployeeService.repositories.PositionRepo
import com.github.vishal1029m.EmployeeService.services.EmployeeService

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
 import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
@Component
class EmployeeServiceImpl(
    @Autowired val employeeRepo: EmployeeRepo,
    @Autowired val modelMapper: ModelMapper,
    @Autowired val positionRepo: PositionRepo,
    @Autowired val departmentRepo: DepartmentRepo
) : EmployeeService {
    override fun getAllEmployees(): List<EmployeeDto> {
        return employeeRepo
            .findAll()
            .stream()
            .map { employee: Employee ->
                modelMapper.map(employee, EmployeeDto::class.java)
            }
            .collect(Collectors.toList())
    }

    override fun getEmployeeById(employeeId: Long): EmployeeDto? {
        val employeeEntity: Employee = employeeRepo
            .findById(employeeId)
            .orElseThrow {
                ResourceNotFoundException("Employee", "Id", employeeId)
            }

        return modelMapper.map(
            employeeEntity, EmployeeDto::class.java
        )
    }

    override fun addEmployee(employeeDto: EmployeeDto): EmployeeDto {
        if (employeeRepo.findById(employeeDto.id!!).isPresent) throw ResourceAlreadyExistException("Employee")

        val employeeEntity: Employee = modelMapper.map(employeeDto, Employee::class.java)
        val position: Position = positionRepo
            .findById(employeeEntity.position.id)
            .orElseThrow {
                ResourceNotFoundException("Position", "Id", employeeEntity.position.id)
            }
        var department: Department = departmentRepo
            .findById(employeeEntity.department.id)
            .orElseThrow {
                ResourceNotFoundException("Department", "Id", employeeEntity.department.id)
            }

        employeeEntity.position = position
        employeeEntity.department = department
        val savedEmployee: Employee = employeeRepo.save(employeeEntity)


        department.employees.add(savedEmployee)
        position.employees.add(savedEmployee)

        return modelMapper.map(
            savedEmployee, EmployeeDto::class.java
        )
    }

    override fun updateEmployee(employeeId: Long, employeeDto: EmployeeDto): EmployeeDto? {
        employeeRepo
            .findById(employeeId)
            .orElseThrow {
                ResourceNotFoundException("Employee", "Id", employeeId)
            }
        var updatedEmployee: Employee = modelMapper.map(employeeDto, Employee::class.java)

        updatedEmployee.position = positionRepo
            .findById(updatedEmployee.position.id)
            .orElseThrow {
                ResourceNotFoundException("Position", "Id", updatedEmployee.position.id)
            }
        updatedEmployee.department = departmentRepo
            .findById(updatedEmployee.department.id)
            .orElseThrow {
                ResourceNotFoundException("Department", "Id", updatedEmployee.department.id)
            }

        return modelMapper.map(
            employeeRepo.save(updatedEmployee), EmployeeDto::class.java
        )
    }

    override fun deleteEmployee(employeeId: Long) {
        employeeRepo
            .findById(employeeId)
            .orElseThrow {
                ResourceNotFoundException("Employee", "Id", employeeId)
            }
        employeeRepo.deleteById(employeeId)
    }
}

