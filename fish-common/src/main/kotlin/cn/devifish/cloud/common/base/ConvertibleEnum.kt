package cn.devifish.cloud.common.base

import java.io.Serializable

/**
 * 用于实现 Spring ConverterFactory 与 JPA AttributeConverter接口
 * 通过 getValue 实现到 Enum 的转换
 *
 * @param <V> getValue 类型
 * @author Devifish
 */
interface ConvertibleEnum<V : Serializable> {

    fun getValue(): V

}