package cn.devifish.cloud.common.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

/**
 * SpringCacheConfiguration Spring Cache 配置
 * 使用Json进行序列化（效率要高于原生JDK序列化框架, 且数据可读性更高）
 *
 * @author Devifish
 */
@Configuration
@EnableCaching
@ConditionalOnClass(RedisConnectionFactory::class)
class SpringCacheConfiguration(
        private val factory: RedisConnectionFactory,
        private val jsonRedisSerializer: Jackson2JsonRedisSerializer<Any>,
        private val stringRedisSerializer: StringRedisSerializer
) : CachingConfigurerSupport() {

    override fun cacheManager(): CacheManager? {
        val config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(7))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer))
                .disableCachingNullValues()

        return RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build()
    }

}