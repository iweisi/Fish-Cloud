package cn.devifish.cloud.admin.common.entity.vo

import cn.devifish.cloud.admin.common.entity.Dept
import cn.devifish.cloud.admin.common.entity.Role
import java.time.LocalDateTime

/**
 * UserVo 用户视图对象
 *
 * @author Devifish
 */
data class UserVo(

        /*ID*/
        var id: Int? = null,

        /*用户名*/
        var username: String? = null,

        /*昵称*/
        var nickname: String? = null,

        /*真实姓名*/
        var realname: String? = null,

        /*部门*/
        var dept: Dept? = null,

        /*角色*/
        var roles: MutableList<Role>? = null,

        /*头像*/
        var avatar: String? = null,

        /*电话号码*/
        var phone: String? = null,

        /*电子邮箱*/
        var email: String? = null,

        /*锁定标记*/
        var lockFlag: Boolean? = null,

        /*创建时间*/
        var createTime: LocalDateTime? = null,

        /*更新时间*/
        var updateTime: LocalDateTime? = null

)