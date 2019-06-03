package cn.devifish.cloud.admin.server.config

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * MybatisPlusConfiguration
 * Mybatis Plus 配置
 *
 * @author Devifish
 */
@Configuration
@EnableTransactionManagement
@MapperScan("cn.devifish.cloud.admin.server.mapper")
class MybatisPlusConfiguration {

    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    fun paginationInterceptor() = PaginationInterceptor()

}