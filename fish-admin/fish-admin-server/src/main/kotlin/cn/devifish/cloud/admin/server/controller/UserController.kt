package cn.devifish.cloud.admin.server.controller

import cn.devifish.cloud.admin.common.entity.Role
import cn.devifish.cloud.admin.common.entity.User
import cn.devifish.cloud.admin.common.entity.vo.UserVo
import cn.devifish.cloud.admin.server.service.DeptService
import cn.devifish.cloud.admin.server.service.RoleService
import cn.devifish.cloud.admin.server.service.UserService
import cn.devifish.cloud.auth.common.annotation.InnerApi
import cn.devifish.cloud.auth.common.util.PasswordEncoderFactory
import cn.devifish.cloud.auth.common.util.SecurityUtils
import cn.devifish.cloud.auth.rpc.RemoteTokenService
import cn.devifish.cloud.common.base.BaseController
import cn.devifish.cloud.common.util.ResultData
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
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
        private val roleService: RoleService,
        private val deptService: DeptService,
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
     * 分页查询用户列表
     *
     * @param page 分页
     * @param deptId 部门ID
     * @param role 角色
     * @return page
     */
    @GetMapping("/list")
    fun list(page: Page<UserVo>, deptId: Int?, role: Role?): ResultData<IPage<UserVo>> {
        var allChildId: List<Int> = emptyList()
        if (deptId == null) return emptyResultData()
        return emptyResultData()
    }

    /**
     * 根据用户名查询用户信息
     * 内部接口
     *
     * @param username 用户名
     * @return UserDetails
     */
    @InnerApi
    @GetMapping("/find/{username}")
    fun find(@PathVariable username: String): ResultData<User> {
        val userInfo = userService.findUserByUsername(username)
        return builderResultData(userInfo)
    }

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    @GetMapping("/exits/{username}")
    fun exits(@PathVariable username: String): ResultData<Boolean> {
        val exitsUsername = userService.exitsUsername(username)
        return ResultData.ok(exitsUsername)
    }

    /**
     * 根据用户ID删除用户
     *
     * @param id 用户ID
     * @return Boolean
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Int): ResultData<Boolean> {
        val user = userService.getById(id)
        return if (user != null) {
            remoteTokenService.logoutAllByUsername(user.username!!)
            userService.removeById(id)
            SUCCESS
        } else ERROR
    }

}