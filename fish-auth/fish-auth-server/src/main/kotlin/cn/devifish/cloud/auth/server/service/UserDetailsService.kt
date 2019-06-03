package cn.devifish.cloud.auth.server.service

import cn.devifish.cloud.admin.common.entity.User
import cn.devifish.cloud.admin.rpc.RemoteUserService
import cn.devifish.cloud.auth.common.entity.Principal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import org.springframework.security.core.userdetails.UserDetailsService as SpringUserDetailsService

/**
 * UserDetailsService
 * 用户信息查询
 *
 * @see org.springframework.security.core.userdetails.UserDetailsService
 * @author Devifish
 */
@Service
class UserDetailsService(
        private val remoteUserService: RemoteUserService
) : SpringUserDetailsService {

    /**
     * 读取用户数据到 Spring Security 鉴权
     * @param username 用户名
     * @return UserDetails 用户信息
     * @throws UsernameNotFoundException 用户名未找到异常
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        val resultData = remoteUserService.find(username!!)
        if (resultData.isNotNull()) {
            val user: User = resultData.data!!

            return Principal(
                    user.id!!,
                    user.username!!,
                    user.password!!,
                    user.deptId!!,
                    !(user.deleteFlag ?: true),
                    true,
                    true,
                    !(user.lockFlag ?: true),
                    Collections.emptySet()
            )
        } else {
            throw UsernameNotFoundException("未找到该账号");
        }
    }

}