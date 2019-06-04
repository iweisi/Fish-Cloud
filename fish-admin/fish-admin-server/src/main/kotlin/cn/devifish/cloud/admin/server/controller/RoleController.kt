package cn.devifish.cloud.admin.server.controller

import cn.devifish.cloud.admin.server.service.RoleService
import cn.devifish.cloud.common.base.BaseController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * RoleController
 * 角色 Controller
 *
 * @author Devifish
 */
@RestController
@RequestMapping("/role")
class RoleController(
        private val roleService: RoleService
): BaseController() {



}