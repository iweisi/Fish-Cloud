package cn.devifish.cloud.common.util

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

    val state: Int
    val data: E?
    val msg: String

    constructor(state: Int, data: E?, msg: String) {
        this.state = state
        this.data = data
        this.msg = msg
    }

    internal constructor(builder: ResultData.Builder<E>) : this(builder.state!!, builder.data, builder.msg!!)

    companion object {

        fun <T> builder(): Builder<T> {
            return ResultData.Builder();
        }

    }

    /**
     * Builder 构造器
     *
     * @param <T> 实体类泛型
     */
    class Builder<T> internal constructor() {

        internal var state: Int? = null
        internal var data: T? = null
        internal var msg: String? = null

        fun state(state: Int): Builder<T> {
            this.state = state
            return this
        }

        fun data(data: T): Builder<T> {
            this.data = data
            return this
        }

        fun msg(msg: String): Builder<T> {
            this.msg = msg
            return this
        }

        fun build(): ResultData<T> {
            return ResultData(this);
        }
    }

}