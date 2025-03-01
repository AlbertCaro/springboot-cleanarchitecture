package dev.albertocaro.backend.domain.usecases.auth

import dev.albertocaro.backend.core.AuthService
import dev.albertocaro.backend.data.UserRepository

class Login(
    private val authService: AuthService,
    private val repository: UserRepository,
) {

    operator fun invoke(username: String, password: String): String? {
        val user = repository.findByUsername(username) ?: return null

        return authService.generateKey(user, password)
    }
}