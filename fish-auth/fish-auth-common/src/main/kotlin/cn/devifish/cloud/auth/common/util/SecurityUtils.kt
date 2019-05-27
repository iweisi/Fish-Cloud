package cn.devifish.cloud.auth.common.util

import cn.devifish.cloud.auth.common.entity.Principal
import cn.devifish.cloud.auth.common.entity.RoleAuthority
import cn.devifish.cloud.common.constant.SecurityConstant
import org.apache.commons.lang3.StringUtils
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import java.util.stream.Collectors

/**
 * Spring Security 工具类
 *
 * @author Devifish
 */
object SecurityUtils {

    /**
     * 初始化一个Null的用户信息， 用于空输出
     */
    private val EMPTY = Principal()

    /**
     * 获取当前用户鉴权信息
     *
     * @return authentication
     */
    private val authentication: Authentication
            get() = SecurityContextHolder.getContext().authentication

    /**
     * 获取当前用户的身份信息
     *
     * @return Principal
     */
    fun getPrincipal(): Principal {
        val principal = authentication.principal
        return if (principal is Principal) principal else EMPTY
    }

    /**
     * 通过前缀搜索 GrantedAuthority 集合中的权限信息
     *
     * @param authorities authorities
     * @param prefix prefix
     * @return 结果集
     */
    fun searchAuthorities(authorities: Collection<GrantedAuthority>?, prefix: String): List<String> {
        if (authorities == null || authorities.isEmpty()) return Collections.emptyList()

        return authorities.stream()
                .map { it.authority }
                .filter { authority -> StringUtils.startsWith(authority, prefix) }
                .map { authority -> StringUtils.removeStart(authority, prefix) }
                .collect(Collectors.toList())
    }

    /**
     * 创建 Authority 集合
     *
     * @param authorities authority
     * @return authorityList
     */
    fun createAuthorityList(vararg authorities: String): Set<GrantedAuthority> {
        return if (authorities.isNotEmpty()) {
            authorities.map { authority ->
                if (StringUtils.startsWith(authority, SecurityConstant.ROLE_PREFIX)) {
                    RoleAuthority(StringUtils.removeStart(authority, SecurityConstant.ROLE_PREFIX))
                } else {
                    SimpleGrantedAuthority(authority)
                }
            }.toSet()
        } else Collections.emptySet()
    }

    /**
     * 获取当前用户角色信息
     *
     * @return roles
     */
    fun getRoles(): Set<String> {
        val authorities = authentication.authorities
        return if (authorities != null && authorities.isNotEmpty()) {
            authorities.stream()
                    .filter { authority -> authority is RoleAuthority }
                    .map { authority -> (authority as RoleAuthority).code }
                    .collect(Collectors.toSet())
        } else Collections.emptySet()
    }

    /**
     * 获取当前用户权限信息
     *
     * @return permissions
     */
    fun getPermissions(): Set<String> {
        val authorities = authentication.authorities
        return if (authorities != null && authorities.isNotEmpty()) {
            authorities.stream()
                    .filter { authority -> authority is SimpleGrantedAuthority }
                    .map { it.authority }
                    .collect(Collectors.toSet())
        } else Collections.emptySet()
    }

}