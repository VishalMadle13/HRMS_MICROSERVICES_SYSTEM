package com.github.vishal1029m.PerformanceReviewService.config.feignconfig

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.vishal1029m.PerformanceReviewService.exceptions.FeignClientCustomException
 import com.github.vishal1029m.PerformanceReviewService.utils.ApiResponse
import feign.Logger
import feign.Response
import feign.codec.ErrorDecoder
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

 @Slf4j
class CustomErrorDecoder(private val objectMapper: ObjectMapper) : ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        com.github.vishal1029m.PerformanceReviewService.config.feignconfig.CustomErrorDecoder.Companion.LOGGER.info("Error Decoder: {}, {}", methodKey,response)
        val content = response?.body()?.asInputStream()?.reader()?.readText()
        val apiResponse = objectMapper.readValue(content, ApiResponse::class.java)
         return FeignClientCustomException(response?.status(), apiResponse?.message ?: "Unknown error")
    }
    companion object {
        private val LOGGER = LoggerFactory.getLogger(com.github.vishal1029m.PerformanceReviewService.config.feignconfig.CustomErrorDecoder::class.java)
    }
}


