package com.github.vishal1029m.EmployeeService.config

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