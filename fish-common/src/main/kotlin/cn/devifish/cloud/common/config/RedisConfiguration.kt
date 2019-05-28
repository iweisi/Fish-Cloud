package cn.devifish.cloud.common.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.*
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 * RedisConfiguration Redis配置
 * Redis 序列化及反序列化实现方式
 * 使用Json进行序列化（效率要高于原生JDK序列化框架, 且数据可读性更高）
 *
 * @author Devifish
 */
@Configuration
@ConditionalOnClass(RedisConnectionFactory::class)
@AutoConfigureBefore(JacksonConfiguration::class)
class RedisConfiguration(
        private val factory: RedisConnectionFactory,
        private val objectMapper: ObjectMapper
) {

    @Bean
    fun redisTemplateBean(): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            keySerializer = stringRedisSerializer()
            hashKeySerializer = stringRedisSerializer()
            valueSerializer = jackson2JsonRedisSerializer()
            hashValueSerializer = jackson2JsonRedisSerializer()
            setConnectionFactory(factory)
        }
    }

    @Bean
    fun jackson2JsonRedisSerializer(): Jackson2JsonRedisSerializer<Any> {
        val jsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        val objectMapper = this.objectMapper.copy().apply {
            setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        }

        jsonRedisSerializer.setObjectMapper(objectMapper)
        return jsonRedisSerializer
    }

    @Bean
    fun stringRedisSerializer(): StringRedisSerializer {
        return StringRedisSerializer()
    }

    @Bean
    fun hashOperations(redisTemplate: RedisTemplate<String, Any>): HashOperations<String, String, Any> {
        return redisTemplate.opsForHash()
    }

    @Bean
    fun valueOperations(redisTemplate: RedisTemplate<String, String>): ValueOperations<String, String> {
        return redisTemplate.opsForValue()
    }

    @Bean
    fun listOperations(redisTemplate: RedisTemplate<String, Any>): ListOperations<String, Any> {
        return redisTemplate.opsForList()
    }

    @Bean
    fun setOperations(redisTemplate: RedisTemplate<String, Any>): SetOperations<String, Any> {
        return redisTemplate.opsForSet()
    }

    @Bean
    fun zSetOperations(redisTemplate: RedisTemplate<String, Any>): ZSetOperations<String, Any> {
        return redisTemplate.opsForZSet()
    }

}