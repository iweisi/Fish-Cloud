package cn.devifish.cloud.registry

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

/**
 * 项目服务注册中心
 * 基于 Spring Cloud Eureka
 *
 * @author Devifish
 */
@EnableEurekaServer
@SpringBootApplication
class RegistryApplication

fun main(args: Array<String>) {
    runApplication<RegistryApplication>(*args)
}