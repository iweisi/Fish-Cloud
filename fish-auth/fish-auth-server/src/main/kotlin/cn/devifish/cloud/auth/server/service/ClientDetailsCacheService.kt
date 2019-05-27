package cn.devifish.cloud.auth.server.service

import cn.devifish.cloud.common.constant.CacheConstant
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
import javax.sql.DataSource

/**
 * ClientDetailsCacheService
 * 重写 ClientDetailsService
 * 对ClientDetails添加到缓存减少数据库IO
 *
 * @author Devifish
 */
open class ClientDetailsCacheService(dataSource: DataSource) : JdbcClientDetailsService(dataSource) {

    @Cacheable(value = [CacheConstant.CLIENT_DETAILS_KEY], key = "#clientId", unless = "#result == null")
    override fun loadClientByClientId(clientId: String?): ClientDetails {
        return super.loadClientByClientId(clientId)
    }

    @Cacheable(value = [CacheConstant.CLIENT_DETAILS_KEY], key = "'ALL_CLIENT_DETAILS'")
    override fun listClientDetails(): MutableList<ClientDetails> {
        return super.listClientDetails()
    }

}