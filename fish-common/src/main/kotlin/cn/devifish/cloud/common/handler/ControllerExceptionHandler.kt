package cn.devifish.cloud.common.handler

import cn.devifish.cloud.common.constant.CommonConstant
import cn.devifish.cloud.common.util.ResultData
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.access.AccessDeniedException
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * ControllerExceptionHandler
 * 全局 Controller 异常处理器
 *
 * @author Devifish
 */
@RestControllerAdvice
class ControllerExceptionHandler {

    private val log = LoggerFactory.getLogger(this.javaClass)

    /**
     * 全局异常默认处理
     *
     * @param exception exception
     * @return boolean
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(exception: Exception): ResultData<Boolean> {
        log.error(exception.message, exception)
        return ResultData.err(false, exception.message ?: CommonConstant.DEFAULT_NULL_MSG)
    }

    /**
     * 访问被拒绝，未授权异常
     *
     * @param exception exception
     * @return boolean
     */
    @ExceptionHandler(AccessDeniedException::class)
    @ConditionalOnClass(AccessDeniedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun accessDeniedException(exception: Exception): ResultData<Boolean> {
        return ResultData.err(false, "无访问权限")
    }

    /**
     * 数据转换异常
     *
     * @param exception exception
     * @return boolean
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun messageNotReadableException(exception: HttpMessageNotReadableException): ResultData<Boolean> {
        log.warn(exception.message)
        return ResultData.warn(false, exception.message ?: CommonConstant.DEFAULT_NULL_MSG)
    }

    /**
     * 数据校验异常
     *
     * @param exception exception
     * @return boolean
     */
    @ExceptionHandler(MethodArgumentNotValidException::class, BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validExceptionHandler(exception: MethodArgumentNotValidException): ResultData<Boolean> {
        return exception.bindingResult
                .fieldErrors
                .stream()
                .map { ResultData.warn(false, "${it.field}: ${it.defaultMessage}") }
                .findFirst()
                .orElse(ResultData.warn(false))
    }

    /**
     * 请求类型不支持异常
     *
     * @param exception exception
     * @return boolean
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun requestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException): ResultData<Boolean> {
        return ResultData.warn(false, exception.message ?: CommonConstant.DEFAULT_NULL_MSG)
    }

}