package cn.devifish.cloud.admin.rpc

import cn.devifish.cloud.admin.common.entity.User
import cn.devifish.cloud.common.constant.ServiceConstant
import cn.devifish.cloud.common.util.ResultData
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * RemoteUserService
 * 用户远程接口
 *
 * @author Devifish
 */
@FeignClient(value = ServiceConstant.ADMIN_API_SERVER)
interface RemoteUserService {

    /**
     * 获取用户详情信息
     *
     * @param username 用户名
     * @return 用户详情信息
     */
    @GetMapping("/user/find/{username}")
    fun find(@PathVariable("username") username: String): ResultData<User>

}