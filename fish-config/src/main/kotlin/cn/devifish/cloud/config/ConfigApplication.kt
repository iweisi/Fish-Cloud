package cn.devifish.cloud.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.config.server.EnableConfigServer

/**
 * ConfigApplication
 * 项目配置服务
 *
 * @author Devifish
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
class ConfigApplication

fun main(args: Array<String>) {
    runApplication<ConfigApplication>(*args)
}