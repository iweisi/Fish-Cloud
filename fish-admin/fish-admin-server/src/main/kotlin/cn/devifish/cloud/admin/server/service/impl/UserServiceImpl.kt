package cn.devifish.cloud.admin.server.service.impl

import cn.devifish.cloud.admin.common.entity.User
import cn.devifish.cloud.admin.server.mapper.UserMapper
import cn.devifish.cloud.admin.server.service.UserService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * UserServiceImpl
 * 用户服务实现
 *
 * @author Devifish
 */
@Service
class UserServiceImpl: ServiceImpl<UserMapper, User>(), UserService {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    override fun findUserByUsername(username: String): User = baseMapper.findUserByUsername(username)

    /**
     * 检查用户是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    override fun exitsUsername(username: String): Boolean = baseMapper.countUsername(username) > 0
}