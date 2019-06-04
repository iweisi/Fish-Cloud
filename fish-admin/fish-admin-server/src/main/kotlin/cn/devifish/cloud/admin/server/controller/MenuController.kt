package cn.devifish.cloud.admin.server.controller

import cn.devifish.cloud.admin.server.service.MenuService
import cn.devifish.cloud.common.base.BaseController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * MenuController
 * 菜单 Controller
 *
 * @author Devifish
 */
@RestController
@RequestMapping("/menu")
class MenuController(
        private val menuService: MenuService
): BaseController() {



}