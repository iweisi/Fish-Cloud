package cn.devifish.cloud.common.annotation

import org.springframework.cloud.openfeign.EnableFeignClients as EnableSpringFeignClients

/**
 * EnableFeignClients
 * Spring Open Feign 自定义注解
 *
 * @author Devifish
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@EnableSpringFeignClients(basePackages = ["cn.devifish.cloud"])
annotation class EnableFeignClients