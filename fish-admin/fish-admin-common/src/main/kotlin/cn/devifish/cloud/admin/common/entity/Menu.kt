package cn.devifish.cloud.admin.common.entity

import cn.devifish.cloud.admin.common.entity.enums.MenuType
import cn.devifish.cloud.common.base.OrderEntity

/**
 * Menu 菜单
 *
 * @author Devifish
 */
class Menu: OrderEntity() {

    /*菜单名*/
    var name: String? = null

    /*图标*/
    var icon: String? = null

    /*路径*/
    var path: String? = null

    /*父级ID*/
    var parentId: Int? = null

    /*权限标识*/
    var permission: String? = null

    /*菜单类型*/
    var type: MenuType? = null

    /*前端组件*/
    var component: String? = null

}