package cn.devifish.cloud.auth.common.annotation

import cn.devifish.cloud.auth.common.config.ResourceServerConfiguration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer as EnableSpringResourceServer

/**
 * EnableResourceServer
 * 自定义开启资源服务注解
 *
 * @author Devifish
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(ResourceServerConfiguration::class)
@EnableSpringResourceServer
annotation class EnableResourceServer