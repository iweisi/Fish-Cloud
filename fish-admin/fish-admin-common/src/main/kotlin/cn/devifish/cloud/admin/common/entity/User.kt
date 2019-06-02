package cn.devifish.cloud.admin.common.entity

import cn.devifish.cloud.common.base.BaseEntity
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

/**
 * User 用户
 *
 * @author Devifish
 */
class User: BaseEntity() {

    /*用户名*/
    @NotBlank
    var username: String? = null

    /*密码*/
    @Min(6)
    var password: String? = null

    /*昵称*/
    var nickname: String? = null

    /*真实姓名*/
    var realname: String? = null

    /*部门ID*/
    var deptId: Int? = null

    /*头像*/
    var avatar: String? = null

    /*电话号码*/
    var phone: String? = null

    /*电子邮箱*/
    @Email
    var email: String? = null

    /*锁定标记*/
    var lockFlag: Boolean? = null

}