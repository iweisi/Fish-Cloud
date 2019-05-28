package cn.devifish.cloud.common.config

import cn.devifish.cloud.common.convert.ConverterEnumFactory
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry

/**
 * ConverterEnumConfiguration 枚举转换配置
 * 注册枚举转换工厂
 *
 * @author Devifish
 */
@Configuration
class ConverterEnumConfiguration(registry: FormatterRegistry) {

    init {
        registry.addConverterFactory(ConverterEnumFactory())
    }

}