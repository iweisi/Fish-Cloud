package cn.devifish.cloud.auth.server.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService as SpringUserDetailsService

/**
 * UserDetailsService
 * 用户信息查询
 *
 * @see org.springframework.security.core.userdetails.UserDetailsService
 * @author Devifish
 */
@Service
class UserDetailsService : SpringUserDetailsService {

    /**
     * 读取用户数据到 Spring Security 鉴权
     * @param username 用户名
     * @return UserDetails 用户信息
     * @throws UsernameNotFoundException 用户名未找到异常
     */
    override fun loadUserByUsername(username: String?): UserDetails? {
        return null
    }

}