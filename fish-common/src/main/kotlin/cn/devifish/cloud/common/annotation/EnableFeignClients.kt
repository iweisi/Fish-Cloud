package cn.devifish.cloud.common.annotation

import org.springframework.cloud.openfeign.EnableFeignClients as EnableSpringFeignClients

/**
 * EnableFeignClients
 *
 * @author Devifish
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@EnableSpringFeignClients(basePackages = ["cn.devifish.cloud"])
annotation class EnableFeignClients