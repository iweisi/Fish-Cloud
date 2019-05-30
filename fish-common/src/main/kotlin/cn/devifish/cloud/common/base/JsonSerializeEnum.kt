package cn.devifish.cloud.common.base

import com.fasterxml.jackson.annotation.JsonValue

/**
 * JsonSerializeEnum
 * 用于实现Jackson序列化枚举特定值
 *
 * @author Devifish
 */
interface JsonSerializeEnum {

    @JsonValue
    fun getJsonValue(): String

}