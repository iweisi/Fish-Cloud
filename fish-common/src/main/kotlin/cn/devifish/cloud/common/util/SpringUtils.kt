package cn.devifish.cloud.common.util

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 * SpringUtils
 * Spring Help 工具类
 *
 * @author Devifish
 */
@Component
@Lazy(false)
object SpringUtils : ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    /**
     * 通过 Bean name 获取Bean
     *
     * @param name bean name
     * @param <T> type
     * @return bean
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getBean(name: String): T? = applicationContext.getBean(name) as T

    /**
     * 通过Bean Class 获取Bean
     *
     * @param beanType class
     * @param <T> type
     * @return bean
     */
    fun <T : Any> getBean(beanType: Class<T>): T? = applicationContext.getBean(beanType)
    fun <T : Any> getBean(beanType: KClass<T>): T? = applicationContext.getBean(beanType.java)

}