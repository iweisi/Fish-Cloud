package cn.devifish.cloud.auth.rpc

import cn.devifish.cloud.common.constant.ServiceConstant
import cn.devifish.cloud.common.util.ResultData
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * RemoteTokenService
 *
 * @author Devifish
 */
@FeignClient(value = ServiceConstant.AUTH_SERVER)
interface RemoteTokenService {

    /**
     * 通过用户名注销该用户所有平台上的Token
     * 内部接口
     *
     * @param username 用户名
     * @return boolean
     */
    @DeleteMapping("/token/logout-all/username")
    fun logoutAllByUsername(@RequestParam("username") username: String): ResultData<Boolean>

    /**
     * 通过角色注销拥有该角色的用户所有平台上的Token (重操作)
     * 使用并行流实现 (多线程遍历提升Token数据量大时的速度)
     * 内部接口
     *
     * @param role 角色Code
     * @return boolean
     */
    @DeleteMapping("/token/logout-all/role")
    fun logoutAllByRole(@RequestParam("role") role: String): ResultData<Boolean>


}