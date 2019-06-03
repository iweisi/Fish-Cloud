package cn.devifish.cloud.admin.server.service

import cn.devifish.cloud.admin.common.entity.User
import com.baomidou.mybatisplus.extension.service.IService

/**
 * UserService
 * 用户服务接口
 *
 * @author Devifish
 */
interface UserService: IService<User> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    fun findUserByUsername(username: String): User

}