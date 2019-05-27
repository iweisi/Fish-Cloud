package cn.devifish.cloud.auth.common.entity

import cn.devifish.cloud.common.constant.SecurityConstant
import org.springframework.security.core.GrantedAuthority

/**
 * RoleAuthority
 *
 * @author Devifish
 */
class RoleAuthority(val code: String) : GrantedAuthority {

    override fun getAuthority(): String = SecurityConstant.ROLE_PREFIX + this.code;

}