package com.github.vishal1029m.AttendanceService.config

import com.github.vishal1029m.AttendanceService.`external services`.EmployeeService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.modelmapper.ModelMapper


@Configuration
class config {
    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }

}