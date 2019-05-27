package cn.devifish.cloud.auth.server

import org.springframework.boot.runApplication
import org.springframework.cloud.client.SpringCloudApplication
import org.springframework.cloud.openfeign.EnableFeignClients

/**
 * AuthApplication 用户认证服务
 *
 * @author Devifish
 */
@EnableFeignClients
@SpringCloudApplication
class AuthApplication

fun main(args: Array<String>) {
    runApplication<AuthApplication>(*args)
}
