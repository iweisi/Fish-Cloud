package cn.devifish.cloud.auth.server.config

import cn.devifish.cloud.auth.common.util.PasswordEncoderFactory
import cn.devifish.cloud.auth.server.service.UserDetailsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Spring Security 配置
 *
 * @see org.springframework.security.config.annotation.SecurityConfigurer
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 * @see org.springframework.security.config.annotation.web.WebSecurityConfigurer
 * @author Devifish
 */
@Order(-1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration(
        private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    @Value("\${security.encryptor:noop}")
    private lateinit var encryptor: String

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/favor.ico")
    }

    /**
     * 取消对oauth 鉴权的拦截
     * 允许跨域访问
     *
     * @param http http
     * @throws Exception exception
     */
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/actuator/**")
                .permitAll()
                .anyRequest()
                .authenticated()

        http.requestMatchers()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/**")
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
    }

    /**
     * 注册用户详情信息服务及密码加密方式
     *
     * @param auth AuthenticationManagerBuilder
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 默认密码加密方式
     *
     * @return PasswordEncoder
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactory.createPasswordEncoder(encryptor)

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

}