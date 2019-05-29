package cn.devifish.cloud.common.util

import cn.devifish.cloud.common.constant.CommonConstant
import java.io.Serializable

/**
 * ResultData
 * API 接口数据返回公共包装类
 * 便于前端页面规范调用数据
 *
 * @param <E> 实体类泛型
 * @author Devifish
 */
class ResultData<E> : Serializable {

    val code: Int
    val data: E?
    val msg: String

    constructor(code: Int, data: E?, msg: String) {
        this.code = code
        this.data = data
        this.msg = msg
    }

    internal constructor(builder: ResultData.Builder<E>)
            : this(builder.code ?: CommonConstant.ERROR_CODE, builder.data,
            builder.msg ?: CommonConstant.DEFAULT_NULL_MSG)

    companion object {

        fun <T> builder(): Builder<T> {
            return ResultData.Builder();
        }

        fun <T> ok(data: T): ResultData<T> {
            return ok(data, CommonConstant.DEFAULT_SUCCESS_MSG)
        }

        fun <T> err(data: T): ResultData<T> {
            return err(data, CommonConstant.DEFAULT_ERROR_MSG)
        }

        fun <T> warn(data: T): ResultData<T> {
            return warn(data, CommonConstant.DEFAULT_WARN_MSG)
        }

        fun <T> ok(data: T, msg: String): ResultData<T> {
            return ResultData(CommonConstant.SUCCESS_CODE, data, msg)
        }

        fun <T> err(data: T, msg: String): ResultData<T> {
            return ResultData(CommonConstant.ERROR_CODE, data, msg)
        }

        fun <T> warn(data: T, msg: String): ResultData<T> {
            return ResultData(CommonConstant.WARN_CODE, data, msg)
        }
    }

    /**
     * Builder 构造器
     *
     * @param <T> 实体类泛型
     */
    class Builder<T> internal constructor() {

        internal var code: Int? = null
        internal var data: T? = null
        internal var msg: String? = null

        fun code(code: Int?): Builder<T> {
            this.code = code
            return this
        }

        fun data(data: T?): Builder<T> {
            this.data = data
            return this
        }

        fun msg(msg: String?): Builder<T> {
            this.msg = msg
            return this
        }

        fun build(): ResultData<T> {
            return ResultData(this);
        }
    }

}