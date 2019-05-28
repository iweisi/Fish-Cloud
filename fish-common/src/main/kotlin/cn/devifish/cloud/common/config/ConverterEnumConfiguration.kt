package cn.devifish.cloud.common.config

import cn.devifish.cloud.common.convert.ConverterEnumFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry


/**
 * ConverterEnumConfiguration
 *
 * @author Devifish
 */
@Configuration
class ConverterEnumConfiguration {

    @Bean
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    fun converterEnumFactory(registry: FormatterRegistry) {
        registry.addConverterFactory(ConverterEnumFactory())
    }

}