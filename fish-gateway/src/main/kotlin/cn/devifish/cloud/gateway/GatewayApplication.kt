package cn.devifish.cloud.gateway

import org.springframework.boot.runApplication
import org.springframework.cloud.client.SpringCloudApplication

/**
 * GatewayApplication
 * 网关服务
 *
 * @author Devifish
 */
@SpringCloudApplication
class GatewayApplication

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}