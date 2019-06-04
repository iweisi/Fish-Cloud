package cn.devifish.cloud.admin.server.service

import cn.devifish.cloud.admin.common.entity.Dept
import com.baomidou.mybatisplus.extension.service.IService

/**
 * DeptService
 * 部门服务接口
 *
 * @author Devifish
 */
interface DeptService: IService<Dept> {

    /**
     * 更新部门层级字段
     *
     * @param dept 部门
     */
    fun updateLevel(dept: Dept)

    /**
     * 通过父级ID判断是否存在子集
     *
     * @param id parentId
     * @return boolean
     */
    fun exitsByPrentId(id: Int): Boolean

    /**
     * 获取该级节点的所有子部门ID
     *
     * @param id ID
     * @return 子部门ID
     */
    fun findAllChildId(id: Int): List<Int>

}