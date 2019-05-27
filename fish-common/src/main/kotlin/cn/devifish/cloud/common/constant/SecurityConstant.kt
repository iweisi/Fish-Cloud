package cn.devifish.cloud.common.constant

/**
 * SecurityConstant
 * Security 相关的常量数据
 *
 * @author Devifish
 */
object SecurityConstant {

    const val OAUTH_HEADER_PREFIX = "Bearer "
    const val INVOKE_TYPE_HEADER = "Invoke-Type"

    const val REFRESH_TOKEN = "refresh_token"

    const val ROLE_PREFIX = "ROLE_"
    const val PERMISSION_PREFIX = "PERM_"

    const val APP_SCOPE = "app"
    const val WEB_SCOPE = "web"
    const val SERVER_SCOPE = "server"

    const val SERVER_CLIENT = "server-client"
    const val BROWSER_CLIENT = "browser-client"

}