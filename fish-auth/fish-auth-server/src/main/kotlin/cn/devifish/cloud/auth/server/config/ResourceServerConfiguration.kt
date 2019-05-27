package cn.devifish.cloud.auth.server.config

import cn.devifish.cloud.auth.common.config.ResourceServiceIgnoreProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import cn.devifish.cloud.auth.common.config.ResourceServerConfiguration as CommonResourceServerConfiguration

/**
 * ResourceServerConfiguration 资源服务配置
 * 用于管理资源拦截
 *
 * @author Devifish
 */
@Configuration
@EnableResourceServer
class ResourceServerConfiguration(
        ignoreProperties: ResourceServiceIgnoreProperties
) : CommonResourceServerConfiguration(null, ignoreProperties) {

    /**
     * OAuth 服务内无需远程访问
     *
     * @param resources ResourceServerSecurityConfigurer
     * @throws Exception Exception
     */
    @Throws(Exception::class)
    override fun configure(resources: ResourceServerSecurityConfigurer) {
    }

}