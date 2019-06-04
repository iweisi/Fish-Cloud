package cn.devifish.cloud.admin.server.service

import cn.devifish.cloud.admin.common.entity.Menu
import com.baomidou.mybatisplus.extension.service.IService

/**
 * MenuService
 * 菜单服务接口
 *
 * @author Devifish
 */
interface MenuService: IService<Menu> {

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