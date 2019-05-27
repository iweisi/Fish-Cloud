package cn.devifish.cloud.registry.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * WebSecurityConfiguration
 *
 * @author Devifish
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http.authorizeRequests()
                .antMatchers("/actuator/**")
                .permitAll()
                .and()
                .csrf()
                .disable()
    }

}