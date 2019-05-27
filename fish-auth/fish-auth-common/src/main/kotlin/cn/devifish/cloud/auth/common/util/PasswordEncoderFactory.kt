package cn.devifish.cloud.auth.common.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.*
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

/**
 * PasswordEncoderFactory
 * 密码加密工厂
 * Spring 5新实现，重写实现快速获取Encoder
 *
 * @see org.springframework.security.crypto.factory.PasswordEncoderFactories
 * @author Devifish
 */
@Suppress("DEPRECATION")
object PasswordEncoderFactory {

    private val encoders: MutableMap<String, PasswordEncoder>

    init {
        encoders = HashMap()
        encoders["bcrypt"] = BCryptPasswordEncoder()
        encoders["ldap"] = LdapShaPasswordEncoder()
        encoders["MD4"] = Md4PasswordEncoder()
        encoders["MD5"] = MessageDigestPasswordEncoder("MD5")
        encoders["noop"] = NoOpPasswordEncoder.getInstance()
        encoders["pbkdf2"] = Pbkdf2PasswordEncoder()
        encoders["scrypt"] = SCryptPasswordEncoder()
        encoders["SHA-1"] = MessageDigestPasswordEncoder("SHA-1")
        encoders["SHA-256"] = MessageDigestPasswordEncoder("SHA-256")
        encoders["sha256"] = StandardPasswordEncoder()
    }

    fun createPasswordEncoder(encodingId: String): PasswordEncoder {
        return DelegatingPasswordEncoder(encodingId, encoders)
    }

}