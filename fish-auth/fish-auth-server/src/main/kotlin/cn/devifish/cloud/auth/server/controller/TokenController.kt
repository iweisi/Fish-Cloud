package cn.devifish.cloud.auth.server.controller

import cn.devifish.cloud.auth.common.annotation.InnerApi
import cn.devifish.cloud.auth.server.service.ClientDetailsCacheService
import cn.devifish.cloud.common.base.BaseController
import cn.devifish.cloud.common.constant.SecurityConstant
import cn.devifish.cloud.common.util.ResultData
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpHeaders
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.web.bind.annotation.*
import java.security.Principal

/**
 * TokenController
 * 令牌
 *
 * @author Devifish
 */
@RestController
@RequestMapping("/token")
class TokenController(
        private val tokenStore: TokenStore,
        private val clientDetailsService: ClientDetailsCacheService
) : BaseController() {

    /**
     * 获取当前用户凭据信息
     *
     * @param principal principal
     * @return principal
     */
    @GetMapping("/current")
    fun current(principal: Principal) = builderResultData(principal)

    /**
     * 用户注销登陆
     *
     * @param tokenHeader token
     * @return boolean
     */
    @DeleteMapping("/logout")
    fun logout(@RequestHeader(HttpHeaders.AUTHORIZATION) tokenHeader: String): ResultData<Boolean> {
        val token = StringUtils.removeStartIgnoreCase(tokenHeader, SecurityConstant.OAUTH_HEADER_PREFIX).trim()
        val accessToken = tokenStore.readAccessToken(token)
        if (accessToken == null || StringUtils.isBlank(accessToken.value)) {
            return ResultData.warn(false, "该 Token 已经失效，无需注销")
        }

        //清除该用户accessToken 及 refreshToken
        tokenStore.removeAccessToken(accessToken)
        tokenStore.removeRefreshToken(accessToken.refreshToken)
        return SUCCESS
    }

    /**
     * 通过用户名注销该用户所有平台上的Token
     * 内部接口
     *
     * @param username 用户名
     * @return boolean
     */
    @InnerApi
    @DeleteMapping("/logout-all/username")
    fun logoutAllByUsername(@RequestParam username: String): ResultData<Boolean> {
        return try {
            val clientDetails = clientDetailsService.listClientDetails()
            clientDetails.flatMap { tokenStore.findTokensByClientIdAndUserName(it.clientId, username) }
                    .forEach { token ->
                        tokenStore.removeAccessToken(token)
                        tokenStore.removeRefreshToken(token.refreshToken)
                    }
            SUCCESS
        } catch (e: Exception) {
            ERROR
        }
    }

    /**
     * 通过角色注销拥有该角色的用户所有平台上的Token (重操作)
     * 使用并行流实现 (多线程遍历提升Token数据量大时的速度)
     * 内部接口
     *
     * @param role 角色Code
     * @return boolean
     */
    @InnerApi
    @DeleteMapping("/logout-all/role")
    fun logoutAllByRole(@RequestParam role: String): ResultData<Boolean> {
        return try {
            val clientDetails = clientDetailsService.listClientDetails()
            clientDetails.flatMap { tokenStore.findTokensByClientId(it.clientId) }
                    .parallelStream()
                    .filter { token ->
                        val code = SecurityConstant.ROLE_PREFIX + role
                        val oAuth2Authentication = tokenStore.readAuthentication(token)
                        oAuth2Authentication.authorities.any { code == it.authority }
                    }
                    .distinct()
                    .forEach { token ->
                        tokenStore.removeAccessToken(token)
                        tokenStore.removeRefreshToken(token.refreshToken)
                    }
            SUCCESS
        } catch (e: Exception) {
            ERROR
        }
    }

}