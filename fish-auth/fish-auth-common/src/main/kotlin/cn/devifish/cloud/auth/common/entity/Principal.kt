package cn.devifish.cloud.auth.common.entity

import cn.devifish.cloud.common.constant.CommonConstant
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.*

/**
 * Principal 用户身份信息
 *
 * @see org.springframework.security.core.userdetails.User
 * @author Devifish
 */
class Principal : User {

    val id: Int
    val deptId: Int

    constructor()
            : this(-1, CommonConstant.NA, CommonConstant.NA, -1, false,
            false, false, false,
            Collections.emptySet())

    constructor(id: Int, username: String, password: String, deptId: Int,
                enabled: Boolean, accountNonExpired: Boolean,
                credentialsNonExpired: Boolean, accountNonLocked: Boolean,
                authorities: Collection<GrantedAuthority>?)
            : super(username, password, enabled,
            accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {

        this.id = id
        this.deptId = deptId
    }

}