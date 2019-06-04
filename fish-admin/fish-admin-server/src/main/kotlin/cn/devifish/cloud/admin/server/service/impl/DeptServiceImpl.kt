package cn.devifish.cloud.admin.server.service.impl

import cn.devifish.cloud.admin.common.entity.Dept
import cn.devifish.cloud.admin.server.mapper.DeptMapper
import cn.devifish.cloud.admin.server.service.DeptService
import cn.devifish.cloud.common.constant.CacheConstant
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.commons.lang3.StringUtils
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.io.Serializable

/**
 * DeptServiceImpl
 * 部门服务实现
 *
 * @author Devifish
 */
@Service
class DeptServiceImpl: ServiceImpl<DeptMapper, Dept>(), DeptService {

    /**
     * 更新部门层级字段
     *
     * @param dept 部门
     */
    override fun updateLevel(dept: Dept) {
        val parentId = dept.parentId
        if (parentId != null) {
            val temp = baseMapper.selectById(parentId)
            val level = temp.level
            if (StringUtils.isNotBlank(level)) {
                dept.level = "$level-$parentId"
            } else {
                dept.level = parentId.toString()
            }
        }
    }

    /**
     * 通过父级ID判断是否存在子集
     *
     * @param id parentId
     * @return boolean
     */
    override fun exitsByPrentId(id: Int): Boolean {
        return baseMapper.countDeptByParentId(id) > 0
    }

    /**
     * 获取该级节点的所有子部门ID
     *
     * @param id ID
     * @return 子部门ID
     */
    @Cacheable(value = [CacheConstant.DEPT_DATA_KEY], key = "'find_all_child:' + #id")
    override fun findAllChildId(id: Int): List<Int> {
        return baseMapper.findAllChildId(id)
    }

    @Cacheable(value = [CacheConstant.DEPT_DATA_KEY], key = "'ALL_DEPT'")
    override fun list(): List<Dept> {
        return super.list()
    }

    @CacheEvict(value = [CacheConstant.DEPT_DATA_KEY], allEntries = true)
    override fun updateById(dept: Dept): Boolean {
        return super.updateById(dept)
    }

    @CacheEvict(value = [CacheConstant.DEPT_DATA_KEY], allEntries = true)
    override fun removeById(id: Serializable): Boolean {
        return super.removeById(id)
    }

}