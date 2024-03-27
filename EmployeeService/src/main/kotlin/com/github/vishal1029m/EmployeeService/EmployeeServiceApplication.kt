package com.github.vishal1029m.EmployeeService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class EmployeeServiceApplication

fun main(args: Array<String>) {
	runApplication<EmployeeServiceApplication>(*args)
}
