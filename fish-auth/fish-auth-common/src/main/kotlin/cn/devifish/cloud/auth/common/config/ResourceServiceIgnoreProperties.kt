package cn.devifish.cloud.auth.common.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * ResourceServiceIgnoreProperties
 * 资源服务路径排除配置
 *
 * @author Devifish
 */
@Configuration
@ConditionalOnExpression("!'\${oauth.resource.ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "oauth.resource.ignore")
class ResourceServiceIgnoreProperties {

    /*放行路径, 不再被安全框架拦截*/
    var paths: MutableList<String> = ArrayList()

}