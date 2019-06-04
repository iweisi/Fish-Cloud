package cn.devifish.cloud.admin.server.mapper

import cn.devifish.cloud.admin.common.entity.Dept
import com.baomidou.mybatisplus.core.mapper.BaseMapper

/**
 * DeptMapper
 * 部门 Mapper
 *
 * @author Devifish
 */
interface DeptMapper: BaseMapper<Dept> {

    /**
     * 根据父级ID统计个数
     *
     * @param id ID
     * @return count
     */
    fun countDeptByParentId(id: Int): Int

    /**
     * 获取该级节点的所有子部门ID
     *
     * @param id ID
     * @return 子部门ID
     */
    fun findAllChildId(id: Int): List<Int>

}