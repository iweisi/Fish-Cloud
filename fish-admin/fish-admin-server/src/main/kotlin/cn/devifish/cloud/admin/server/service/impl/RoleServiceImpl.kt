package cn.devifish.cloud.admin.server.service.impl

import cn.devifish.cloud.admin.common.entity.Role
import cn.devifish.cloud.admin.server.mapper.RoleMapper
import cn.devifish.cloud.admin.server.service.RoleService
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * RoleServiceImpl
 * 角色服务实现
 *
 * @author Devifish
 */
@Service
class RoleServiceImpl: ServiceImpl<RoleMapper, Role>(), RoleService {

    /**
     * 通过用户ID查询角色信息
     *
     * @param userId 用户ID
     * @return 角色
     */
    override fun findRolesByUserId(userId: Int) = baseMapper.findRolesByUserId(userId)

    /**
     * 根据部门ID分页查询角色信息
     *
     * @param page 分页数据
     * @param deptIds 部门ID
     * @return 角色
     */
    override fun findRolePageByDeptIds(page: Page<Role>, deptIds: Array<Int>) = baseMapper.findRolePageByDeptIds(page, deptIds)
}