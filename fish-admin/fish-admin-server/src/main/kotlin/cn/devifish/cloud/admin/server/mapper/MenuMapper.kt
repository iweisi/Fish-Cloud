package cn.devifish.cloud.admin.server.mapper

import cn.devifish.cloud.admin.common.entity.Menu
import com.baomidou.mybatisplus.core.mapper.BaseMapper

/**
 * MenuMapper
 * 菜单 Mapper
 *
 * @author Devifish
 */
interface MenuMapper: BaseMapper<Menu> {

    /**
     * 获取该级节点的所有子菜单ID
     *
     * @param id ID
     * @return 子菜单ID
     */
    fun findAllChildId(id: Int): List<Int>

    /**
     * 查询子菜单
     *
     * @return MenuList
     */
    fun findChildMenuList(id: Int): List<Menu>

}