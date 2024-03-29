package com.github.vishal1029m.PayrollService.config.feignconfig

import com.fasterxml.jackson.databind.ObjectMapper
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration(private val objectMapper: ObjectMapper) {
    @Bean
    fun customErrorDecoder(): ErrorDecoder {
        return com.github.vishal1029m.PayrollService.config.feignconfig.CustomErrorDecoder(objectMapper)
    }
}
