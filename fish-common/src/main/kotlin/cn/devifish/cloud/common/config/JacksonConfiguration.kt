package cn.devifish.cloud.common.config

import cn.devifish.cloud.common.constant.CommonConstant
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*


/**
 * JacksonConfiguration
 * Jackson Spring 配置
 *
 * @author Devifish
 */
@Configuration
@ConditionalOnClass(ObjectMapper::class)
@AutoConfigureBefore(JacksonAutoConfiguration::class)
class JacksonConfiguration {

    @Bean
    fun getObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()

        //针对Kotlin 及 JDK 1.8 新特性注册模块
        val jdk8Module = Jdk8Module()
        val kotlinModule = KotlinModule()
        val parameterNamesModule = ParameterNamesModule()
        val javaTimeModule = JavaTimeModule().apply {
            val dateTimeFormatter = DateTimeFormatter.ofPattern(CommonConstant.DATE_TIME_PATTERN)
            val dateFormatter = DateTimeFormatter.ofPattern(CommonConstant.DATE_PATTERN)
            val timeFormatter = DateTimeFormatter.ofPattern(CommonConstant.TIME_PATTERN)

            addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(dateTimeFormatter))
            addSerializer(LocalDate::class.java, LocalDateSerializer(dateFormatter))
            addSerializer(LocalTime::class.java, LocalTimeSerializer(timeFormatter))
            addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer(dateTimeFormatter))
            addDeserializer(LocalDate::class.java, LocalDateDeserializer(dateFormatter))
            addDeserializer(LocalTime::class.java, LocalTimeDeserializer(timeFormatter))
        }

        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))
        objectMapper.registerModules(
                javaTimeModule,
                parameterNamesModule,
                jdk8Module,
                kotlinModule
        )

        //忽略不识别及无法转换的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)

        return objectMapper
    }

}