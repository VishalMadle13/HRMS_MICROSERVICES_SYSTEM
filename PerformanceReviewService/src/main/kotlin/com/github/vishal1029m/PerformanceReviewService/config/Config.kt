package com.github.vishal1029m.PerformanceReviewService.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.modelmapper.ModelMapper


@Configuration
class Config {
    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }

}