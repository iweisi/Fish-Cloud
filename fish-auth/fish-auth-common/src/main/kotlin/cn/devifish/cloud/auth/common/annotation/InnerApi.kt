package cn.devifish.cloud.auth.common.annotation

import cn.devifish.cloud.common.constant.SecurityConstant
import org.springframework.security.access.prepost.PreAuthorize

/**
 * InnerApi
 * 内部接口专用注解
 * 用于防止外部用户调用， 仅用于服务Client 调用
 *
 * @author Devifish
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("principal == '${SecurityConstant.SERVER_CLIENT}'")
annotation class InnerApi