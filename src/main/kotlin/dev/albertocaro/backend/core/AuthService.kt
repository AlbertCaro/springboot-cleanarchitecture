package dev.albertocaro.backend.core

import dev.albertocaro.backend.domain.models.User
import io.jsonwebtoken.Jwts
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Service
class AuthService(
    private val passwordEncoderService: PasswordEncoderService,
) {

    fun generateKey(user: User, password: String): String? {
        return if (passwordEncoderService.matches(user, password)) {
            generateToken(user.username)
        } else {
            return null
        }
    }

    fun generateToken(username: String): String {
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de expiración
            .signWith(SecretKeySpec(SECRET_KEY.toByteArray(), "HmacSHA256"))
            .compact()
    }

    fun getAuthenticationUsername(): String? {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication != null && authentication.principal is String) {
            return authentication.principal as String
        }

        return null
    }

    companion object {
        const val SECRET_KEY = "e34a602468ef22a894a996d41f6a2bfcdf1e3f04182aeb2439897a4447534d93"
    }
}