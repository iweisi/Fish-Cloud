package cn.devifish.cloud.auth.common.config

import cn.devifish.cloud.auth.common.convert.PrincipalAuthenticationConverter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.RemoteTokenServices

/**
 * ResourceServerConfiguration
 * 资源服务公共配置
 *
 * @author Devifish
 */
open class ResourceServerConfiguration(
        private val remoteTokenServices: RemoteTokenServices?,
        private val ignoreProperties: ResourceServiceIgnoreProperties
) : ResourceServerConfigurerAdapter() {

    /**
     * 读取配置内不需要拦截的接口
     * 及其它公共配置
     *
     * @param http HttpSecurity
     */
    override fun configure(http: HttpSecurity) {
        val registry = http.authorizeRequests()

        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable()

        //排除无需拦截接口
        val ignoreUrls = ignoreProperties.paths
        if (ignoreUrls.isNotEmpty()) registry.antMatchers(*ignoreUrls.toTypedArray()).permitAll()

        //拦截其他接口及csrf 跨域
        registry.anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
    }

    /**
     * 覆盖Auth资源服务获取用户信息方式
     *
     * @param resources ResourceServerSecurityConfigurer
     */
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        super.configure(resources)

        val userAuthenticationConverter = PrincipalAuthenticationConverter()
        val accessTokenConverter = DefaultAccessTokenConverter().apply {
            setUserTokenConverter(userAuthenticationConverter)
        }
        remoteTokenServices!!.setAccessTokenConverter(accessTokenConverter)
        resources.tokenServices(remoteTokenServices)
    }

}