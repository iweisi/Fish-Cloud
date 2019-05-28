package cn.devifish.cloud.common.config

import feign.Logger
import feign.RequestInterceptor
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails

/**
 * Feign 客户端配置
 * 使其rpc支持OAuth2鉴权访问及远程文件上传（表单提交）
 *
 * @author Devifish
 */
@Configuration
class OpenFeignConfiguration(
        private val messageConverters: ObjectFactory<HttpMessageConverters>
) {

    /**
     * Feign 访问服务携带OAuth2 Token
     *
     * @return RequestInterceptor
     */
    @Bean
    @ConditionalOnClass(OAuth2ClientContext::class)
    @ConditionalOnProperty("security.oauth2.client.client-id")
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    fun oAuth2RequestInterceptor(oAuth2ClientContext: OAuth2ClientContext,
                                 resourceDetails: OAuth2ProtectedResourceDetails): RequestInterceptor {
        return OAuth2FeignRequestInterceptor(oAuth2ClientContext, resourceDetails)
    }

    /**
     * 使用Spring MVC上注册的消息转换器
     *
     * @return Encoder
     */
    @Bean
    fun feignFormEncoder(): Encoder {
        return SpringFormEncoder(SpringEncoder(messageConverters))
    }

    /**
     * 配置Feign 日志输出类型
     *
     * @return Logger
     */
    @Bean
    fun logger(): Logger.Level {
        return Logger.Level.NONE
    }

}