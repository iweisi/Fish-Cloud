package cn.devifish.cloud.admin.server.mapper

import cn.devifish.cloud.admin.common.entity.User
import com.baomidou.mybatisplus.core.mapper.BaseMapper

/**
 * UserMapper
 * 用户 Mapper
 *
 * @author Devifish
 */
interface UserMapper: BaseMapper<User> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    fun findUserByUsername(username: String): User

    /**
     * 统计用户名数量
     * 用于检测用户名是否存在
     *
     * @param username 用户名
     * @return count
     */
    fun countUsername(username: String): Int

}