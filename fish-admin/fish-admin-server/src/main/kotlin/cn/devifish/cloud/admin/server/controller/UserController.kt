package cn.devifish.cloud.admin.server.controller

import cn.devifish.cloud.admin.common.entity.User
import cn.devifish.cloud.admin.server.service.UserService
import cn.devifish.cloud.auth.common.util.PasswordEncoderFactory
import cn.devifish.cloud.auth.common.util.SecurityUtils
import cn.devifish.cloud.auth.rpc.RemoteTokenService
import cn.devifish.cloud.common.base.BaseController
import cn.devifish.cloud.common.util.ResultData
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct

/**
 * UserController
 * 用户 Controller
 *
 * @author Devifish
 */
@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService,
        private val remoteTokenService: RemoteTokenService
): BaseController() {

    @Value("\${security.encryptor:noop}")
    private lateinit var encryptor: String
    private lateinit var passwordEncoder: PasswordEncoder

    @PostConstruct
    fun init() {
        this.passwordEncoder = PasswordEncoderFactory.createPasswordEncoder(encryptor)
    }

    /**
     * 查询当前用户信息
     *
     * @return UserVo
     */
    @GetMapping("/current")
    fun current(): ResultData<User> {
        val principal = SecurityUtils.getPrincipal()
        return if (principal.id > 0) {
            val user = userService.getById(principal.id)
            builderResultData(user)
        }else emptyResultData()
    }

    /**
     * 根据用户ID删除用户
     *
     * @param id 用户ID
     * @return Boolean
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Int?): ResultData<Boolean> {
        val user = userService.getById(id)
        return if (user?.username != null) {
            remoteTokenService.logoutAllByUsername(user.username!!)
            userService.removeById(id)
            SUCCESS
        } else ERROR
    }

}