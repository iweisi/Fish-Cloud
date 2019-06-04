package cn.devifish.cloud.admin.server.service.impl

import cn.devifish.cloud.admin.common.entity.Menu
import cn.devifish.cloud.admin.server.mapper.MenuMapper
import cn.devifish.cloud.admin.server.service.MenuService
import cn.devifish.cloud.common.constant.CacheConstant
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.io.Serializable

/**
 * MenuServiceImpl
 * 菜单服务实现
 *
 * @author Devifish
 */
@Service
class MenuServiceImpl: ServiceImpl<MenuMapper, Menu>(), MenuService {

    /**
     * 获取该级节点的所有子菜单ID
     *
     * @param id ID
     * @return 子菜单ID
     */
    override fun findAllChildId(id: Int) = baseMapper.findAllChildId(id)

    /**
     * 查询子菜单
     *
     * @return MenuList
     */
    override fun findChildMenuList(id: Int) = baseMapper.findChildMenuList(id)

    @Cacheable(value = [CacheConstant.MENU_DATA_KEY], key = "'ALL_MENU'")
    override fun list(): MutableList<Menu> {
        return super<MenuService>.list()
    }

    @CacheEvict(value = [CacheConstant.MENU_DATA_KEY], allEntries = true)
    override fun save(entity: Menu): Boolean {
        return super.save(entity)
    }

    @CacheEvict(value = [CacheConstant.MENU_DATA_KEY], allEntries = true)
    override fun updateById(entity: Menu): Boolean {
        return super.updateById(entity)
    }

    @CacheEvict(value = [CacheConstant.MENU_DATA_KEY], allEntries = true)
    override fun removeById(id: Serializable): Boolean {
        return super.removeById(id)
    }

}