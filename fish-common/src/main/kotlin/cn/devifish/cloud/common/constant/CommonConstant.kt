package cn.devifish.cloud.common.constant

/**
 * 公共常量数据
 *
 * @author Devifish
 */
object CommonConstant {

    const val NA = "N/A"

    /**
     * 框架公共的时间戳格式
     * 统一时间格式
     */
    const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    const val DATE_PATTERN = "yyyy-MM-dd"
    const val TIME_PATTERN = "HH:mm:ss"

    /**
     * 各种状态的 Code 默认值
     */
    const val ERROR_CODE: Int = 0
    const val SUCCESS_CODE: Int = 1
    const val WARN_CODE: Int = 2

    /**
     * 各种状态的 Message 默认值
     */
    const val DEFAULT_ERROR_MSG = "error"
    const val DEFAULT_SUCCESS_MSG = "success"
    const val DEFAULT_WARN_MSG = "warn"
    const val DEFAULT_NULL_MSG = "null"

}