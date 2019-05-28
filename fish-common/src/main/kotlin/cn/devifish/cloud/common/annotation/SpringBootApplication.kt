package cn.devifish.cloud.common.annotation

import org.springframework.boot.autoconfigure.SpringBootApplication as SpringBoot

/**
 * SpringBootApplication
 *
 * @author Devifish
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SpringBoot(scanBasePackages = ["cn.devifish.cloud"])
annotation class SpringBootApplication