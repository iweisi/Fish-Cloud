package cn.devifish.cloud.admin.server.mapper

import cn.devifish.cloud.admin.common.entity.Role
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page


/**
 * RoleMapper
 * 角色 Mapper
 *
 * @author Devifish
 */
interface RoleMapper: BaseMapper<Role> {

    /**
     * 通过用户ID查询角色信息
     *
     * @param userId 用户ID
     * @return 角色
     */
    fun findRolesByUserId(userId: Int): List<Role>

    /**
     * 根据部门ID分页查询角色信息
     *
     * @param page 分页数据
     * @param deptIds 部门ID
     * @return 角色
     */
    fun findRolePageByDeptIds(page: Page<Role>, deptIds: Array<Int>): IPage<Role>

}