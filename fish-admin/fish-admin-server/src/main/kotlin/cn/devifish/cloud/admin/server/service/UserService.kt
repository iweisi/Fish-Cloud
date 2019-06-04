package cn.devifish.cloud.admin.server.service

import cn.devifish.cloud.admin.common.entity.User

/**
 * UserService
 * 用户服务接口
 *
 * @author Devifish
 */
interface UserService {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    fun findUserByUsername(username: String): User

    /**
     * 检查用户是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    fun exitsUsername(username: String): Boolean

}