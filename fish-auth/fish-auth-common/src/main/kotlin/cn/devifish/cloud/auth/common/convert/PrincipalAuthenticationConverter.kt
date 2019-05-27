package cn.devifish.cloud.auth.common.convert

import cn.devifish.cloud.auth.common.entity.Principal
import cn.devifish.cloud.auth.common.util.SecurityUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter.AUTHORITIES
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter.USERNAME
import org.springframework.util.StringUtils
import java.util.*
import kotlin.collections.HashMap

/**
 * PrincipalAuthenticationConverter
 * 重写Spring Security 默认实现
 * Token 转换 Principal 用户信息
 *
 * @see org.springframework.security.oauth2.provider.token.UserAuthenticationConverter
 * @see org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter
 * @author Devifish
 */
class PrincipalAuthenticationConverter : UserAuthenticationConverter {

    companion object {
        private const val USER_ID = "id"
        private const val DEPT_ID = "dept_id"
    }

    /**
     * 序列化Authentication
     * 便于服务间传输
     *
     * @param authentication 身份信息
     * @return Map
     */
    override fun convertUserAuthentication(authentication: Authentication): Map<String, *> {
        val response = HashMap<String, Any?>()
        response[USERNAME] = authentication.name
        if (authentication.authorities != null && !authentication.authorities.isEmpty()) {
            response[AUTHORITIES] = AuthorityUtils.authorityListToSet(authentication.authorities)
        }

        val principal = authentication.principal
        if (principal is Principal) {
            response[USER_ID] = principal.id
            response[DEPT_ID] = principal.deptId
        }

        return response
    }

    /**
     * 反序列化Authentication
     * 便于服务间传输
     *
     * @param map Map
     * @return 身份信息
     */
    override fun extractAuthentication(map: Map<String, *>): Authentication? {
        return if (map.containsKey(USERNAME)) {
            val id = map[USER_ID] as Int
            val username = map[USERNAME] as String
            val deptId = map[DEPT_ID] as Int
            val authorities = getAuthorities(map)

            val principal = Principal(id, username, "N/A", deptId, true,
                    true, true, true, authorities)
            UsernamePasswordAuthenticationToken(principal, "N/A", authorities)
        } else null
    }

    private fun getAuthorities(map: Map<String, *>): Collection<GrantedAuthority> {
        return when (val authorities = map[AUTHORITIES]) {
            is String -> SecurityUtils.createAuthorityList(*StringUtils.tokenizeToStringArray(authorities, ","))
            is Collection<*> -> {
                @Suppress("UNCHECKED_CAST")
                val collection = authorities as Collection<String>
                SecurityUtils.createAuthorityList(*collection.toTypedArray())
            }
            else -> Collections.emptySet()
        }
    }

}