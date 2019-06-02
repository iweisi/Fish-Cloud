package cn.devifish.cloud.admin.server

import cn.devifish.cloud.auth.common.annotation.EnableResourceServer
import cn.devifish.cloud.common.annotation.EnableFeignClients
import org.springframework.boot.runApplication
import org.springframework.cloud.client.SpringCloudApplication

/**
 * AdminApplication 系统后台管理服务
 *
 * @author Devifish
 */
@EnableResourceServer
@EnableFeignClients
@SpringCloudApplication
class AdminApplication

fun main(args: Array<String>) {
    runApplication<AdminApplication>(*args)
}

