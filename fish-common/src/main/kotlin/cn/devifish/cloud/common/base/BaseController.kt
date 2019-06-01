package cn.devifish.cloud.common.base

import cn.devifish.cloud.common.constant.CommonConstant
import cn.devifish.cloud.common.constant.SecurityConstant
import cn.devifish.cloud.common.util.ResultData
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import javax.servlet.http.HttpServletRequest


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
                .code(CommonConstant.ERROR_CODE)
                .msg(CommonConstant.DEFAULT_NULL_MSG)
                .build()

        val SUCCESS = ResultData.ok(true)
        val ERROR = ResultData.err(false)
        val WARN = ResultData.warn(false)
    }

    @Autowired(required = false)
    protected lateinit var request: HttpServletRequest

    /**
     * 获取公共NULL值ResultData
     * 减少重复NULL或NULL值ResultData创建，产生无用的内存开销
     *
     * @param <T> 传输对象泛型
     * @return ResultData
    </T> */
    @Suppress("UNCHECKED_CAST")
    protected fun <T> emptyResultData() = EMPTY as ResultData<T>

    /**
     * 快速构建不带message的ResultData
     * 内部进行空判断，非空时返回ok，错误时返回err
     * 并对空对象，空集合，空键值直接返回emptyResultData
     *
     * @param data 传输对象
     * @param <T> 传输对象泛型
     * @return ResultData
    </T> */
    protected fun <T> builderResultData(data: T?): ResultData<T> {
        return if (data == null || data is Collection<*> && (data as Collection<*>).isEmpty()
                || data is Map<*, *> && (data as Map<*, *>).isEmpty()) {
            emptyResultData()
        } else ResultData.ok(data)
    }

    /**
     * 快速构建带message的ResultData
     * 内部进行空判断，非空时返回ok，错误时返回err
     *
     * @param data 传输对象
     * @param msg 返回消息
     * @param <T> 传输对象泛型
     * @return ResultData
    </T> */
    protected fun <T> builderResultData(data: T?, msg: String): ResultData<T> {
        return builderResultData(data, msg, msg)
    }

    /**
     * 快速构建带message的ResultData
     * 内部进行空判断，非空时返回ok，错误时返回err
     *
     * @param data 传输对象
     * @param msg 返回消息
     * @param error 错误消息
     * @param <T> 传输对象泛型
     * @return ResultData
    </T> */
    protected fun <T> builderResultData(data: T?, msg: String, error: String): ResultData<T> {
        return if (data == null || data is Collection<*> && (data as Collection<*>).isEmpty()
                || data is Map<*, *> && (data as Map<*, *>).isEmpty()) {
            ResultData.err(null, error)
        } else ResultData.ok(data, msg)

    }

    /**
     * 获取当前用户Token
     *
     * @return token
     */
    protected fun currentUserAccessToken(): String? {
        val token = this.request.getHeader(HttpHeaders.AUTHORIZATION)
        return if (StringUtils.startsWithIgnoreCase(token, SecurityConstant.OAUTH_HEADER_PREFIX))
            StringUtils.removeStartIgnoreCase(token, SecurityConstant.OAUTH_HEADER_PREFIX)
        else null
    }

}