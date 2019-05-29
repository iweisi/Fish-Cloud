package cn.devifish.cloud.common.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.StringUtils

/**
 * JsonUtils
 * Json 转换工具 基于 jackson
 * 依赖Spring 环境
 *
 * @see com.fasterxml.jackson.databind.ObjectMapper
 * @author Devifish
 */
object JsonUtils {

    private val objectMapper: ObjectMapper by lazy {
        SpringUtils.getBean(ObjectMapper::class)
    }

    fun toJson(data: Any) = objectMapper.writeValueAsString(data)

    fun <T : Any> toObject(json: String?, clazz: Class<T>): T? {
        return if (StringUtils.isNoneBlank(json)) {
            objectMapper.readValue(json, clazz)
        }else null
    }

}