package cn.devifish.cloud.common.constant

/**
 * 缓存相关的常量数据
 *
 * @author Devifish
 */
object CacheConstant {

    /**
     * 缓存key前缀
     * 用户区分各个服务的缓存数据
     */
    const val AUTH_SERVER_PREFIX = "oauth:"
    const val ADMIN_SERVER_PREFIX = "admin:"

    /**
     * OAuth2 服务
     */
    const val CLIENT_DETAILS_KEY = "${AUTH_SERVER_PREFIX}client_details"

    /**
     * Admin 服务
     */
    const val USER_DATA_KEY = "${ADMIN_SERVER_PREFIX}user_data"
    const val MENU_DATA_KEY = "${ADMIN_SERVER_PREFIX}menu_data"
    const val ROLE_DATA_KEY = "${ADMIN_SERVER_PREFIX}role_data"
    const val DEPT_DATA_KEY = "${ADMIN_SERVER_PREFIX}dept_data"

}