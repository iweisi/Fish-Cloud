package cn.devifish.cloud.admin.common.entity

import cn.devifish.cloud.common.base.OrderEntity

/**
 * Dept 部门
 *
 * @author Devifish
 */
data class Dept(

        /*部门名称*/
        var name: String? = null,

        /*描述*/
        var description: String? = null,

        /*父级ID*/
        var parentId: Int? = null,

        /*层级结构*/
        var level: String? = null

) : OrderEntity()