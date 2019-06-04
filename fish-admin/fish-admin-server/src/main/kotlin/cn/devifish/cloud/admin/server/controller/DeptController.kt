package cn.devifish.cloud.admin.server.controller

import cn.devifish.cloud.admin.server.service.DeptService
import cn.devifish.cloud.common.base.BaseController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * DeptController
 * 部门 Controller
 *
 * @author Devifish
 */
@RestController
@RequestMapping("/dept")
class DeptController(
        private val deptService: DeptService
): BaseController() {



}