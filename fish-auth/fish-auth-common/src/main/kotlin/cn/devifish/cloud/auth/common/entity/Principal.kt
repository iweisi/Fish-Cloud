package cn.devifish.cloud.auth.common.entity

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

    val id: Int?
    val deptId: Int?

    constructor()
            : this(null, "", "", null, false,
            false, false, false,
            Collections.emptySet())

    constructor(id: Int?, username: String?, password: String?, deptId: Int?,
                enabled: Boolean, accountNonExpired: Boolean,
                credentialsNonExpired: Boolean, accountNonLocked: Boolean,
                authorities: Collection<GrantedAuthority>?)
            : super(username, password, enabled,
            accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {

        this.id = id
        this.deptId = deptId
    }

}