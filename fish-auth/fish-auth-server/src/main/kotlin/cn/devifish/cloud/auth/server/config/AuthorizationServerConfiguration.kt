package cn.devifish.cloud.auth.server.config

import cn.devifish.cloud.auth.common.convert.PrincipalAuthenticationConverter
import cn.devifish.cloud.auth.server.service.ClientDetailsCacheService
import cn.devifish.cloud.auth.server.service.UserDetailsService
import cn.devifish.cloud.common.constant.CacheConstant
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import javax.sql.DataSource

/**
 * OAuth2 身份认证服务配置
 *
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
 * @author Devifish
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration(
        private val dataSource: DataSource,
        private val redisConnectionFactory: RedisConnectionFactory,
        private val authenticationManager: AuthenticationManager,
        private val userDetailsService: UserDetailsService
) : AuthorizationServerConfigurerAdapter() {

    @Value("\${security.oauth2.accessTokenValidity}")
    private var accessTokenValidity: Int = 0

    @Value("\${security.oauth2.refreshTokenValidity}")
    private var refreshTokenValidity: Int = 0

    /**
     * 开启权限认证接口
     * /oauth/check_token 及 /oauth/token_key
     *
     * @param security security
     * @throws Exception exception
     */
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    /**
     * 配置授权客户端，用于可信客户端授权
     *
     * @param clients clients
     * @throws Exception exception
     */
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.withClientDetails(clientDetails())
    }

    /**
     * 配置授权、令牌的访问服务 用于账户密码授权
     *
     * @param endpoints spring security
     */
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
        endpoints.clientDetailsService = clientDetails()

        // Token 内容身份信息转换
        val authenticationConverter = PrincipalAuthenticationConverter()
        endpoints.accessTokenConverter(DefaultAccessTokenConverter().apply {
            setUserTokenConverter(authenticationConverter)
        })

        // Token 有效时间设置
        endpoints.tokenServices(DefaultTokenServices().apply {
            setTokenStore(endpoints.tokenStore)
            setTokenEnhancer(endpoints.tokenEnhancer)
            setClientDetailsService(endpoints.clientDetailsService)
            setSupportRefreshToken(true)
            setAccessTokenValiditySeconds(accessTokenValidity)
            setRefreshTokenValiditySeconds(refreshTokenValidity)
        })
    }

    @Bean
    fun clientDetails(): ClientDetailsCacheService = ClientDetailsCacheService(dataSource)

    /**
     * Redis 数据库缓存鉴权 Token
     *
     * @return TokenStore
     */
    @Bean
    fun tokenStore(): TokenStore {
        val tokenStore = RedisTokenStore(redisConnectionFactory)
        tokenStore.setPrefix(CacheConstant.AUTH_SERVER_PREFIX)
        return tokenStore
    }

}