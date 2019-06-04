package cn.devifish.cloud.admin.server.service

import cn.devifish.cloud.admin.common.entity.Role
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.IService

/**
 * RoleService
 * 角色服务接口
 *
 * @author Devifish
 */
interface RoleService: IService<Role> {

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