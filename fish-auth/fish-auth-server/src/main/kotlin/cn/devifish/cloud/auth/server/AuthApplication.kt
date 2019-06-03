package cn.devifish.cloud.auth.server

import cn.devifish.cloud.common.annotation.EnableFeignClients
import org.springframework.boot.runApplication
import org.springframework.cloud.client.SpringCloudApplication

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
