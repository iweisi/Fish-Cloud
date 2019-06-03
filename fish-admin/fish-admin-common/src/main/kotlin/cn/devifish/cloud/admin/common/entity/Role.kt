package cn.devifish.cloud.admin.common.entity

import cn.devifish.cloud.common.base.BaseEntity
import javax.validation.constraints.NotBlank

/**
 * Role 角色
 *
 * @author Devifish
 */
data class Role(

        /*角色名*/
        @NotBlank
        var name: String? = null,

        /*角色标识*/
        @NotBlank
        var code: String? = null,

        /*描述*/
        var description: String? = null,

        /*角色权限*/
        var authorities: HashSet<String>? = null,

        /*部门ID*/
        var deptId: Int? = null

) : BaseEntity()