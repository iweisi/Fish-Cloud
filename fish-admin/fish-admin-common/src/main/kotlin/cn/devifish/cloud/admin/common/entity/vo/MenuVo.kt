package cn.devifish.cloud.admin.common.entity.vo

import cn.devifish.cloud.admin.common.entity.enums.MenuType

/**
 * MenuVo 菜单视图对象
 *
 * @author Devifish
 */
data class MenuVo(

        /*菜单名*/
        var name: String? = null,

        /*图标*/
        var icon: String? = null,

        /*路径*/
        var path: String? = null,

        /*菜单类型*/
        var type: MenuType? = null,

        /*前端组件*/
        var component: String? = null,

        /*子菜单*/
        var children: ArrayList<MenuVo>? = null

)