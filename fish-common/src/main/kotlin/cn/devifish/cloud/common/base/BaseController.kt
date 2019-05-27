package cn.devifish.cloud.common.base

import cn.devifish.cloud.common.util.ResultData

/**
 * BaseController
 * Controller 基类
 * 对一些常用操作进行封装，方便编码
 *
 * @author Devifish
 */
abstract class BaseController {

    companion object {
        val EMPTY = ResultData.builder<Any>()
                .state(0)
                .msg("")
                .build()
    }

}