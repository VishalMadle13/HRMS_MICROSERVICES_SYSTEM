package com.github.vishal1029m.PerformanceReviewService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class PerformanceReviewServiceApplication

fun main(args: Array<String>) {
	runApplication<com.github.vishal1029m.PerformanceReviewService.PerformanceReviewServiceApplication>(*args)
}
