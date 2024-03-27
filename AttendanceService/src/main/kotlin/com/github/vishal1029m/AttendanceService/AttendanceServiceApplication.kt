package com.github.vishal1029m.AttendanceService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class AttendanceServiceApplication

fun main(args: Array<String>) {
	runApplication<AttendanceServiceApplication>(*args)
}
