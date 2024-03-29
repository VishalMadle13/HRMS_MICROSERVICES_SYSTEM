package com.github.vishal1029m.PayrollService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class PayrollServiceApplication

fun main(args: Array<String>) {
	runApplication<com.github.vishal1029m.PayrollService.PayrollServiceApplication>(*args)
}
