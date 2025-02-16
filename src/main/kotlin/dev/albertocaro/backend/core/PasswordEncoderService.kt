package dev.albertocaro.backend.core

import dev.albertocaro.backend.domain.models.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordEncoderService(
    private val passwordEncoder: PasswordEncoder
) {
    fun encode(string: String): String? {
        return passwordEncoder.encode(string)
    }

    fun matches(user: User, password: String): Boolean {
        return passwordEncoder.matches(password, user.password)
    }
}