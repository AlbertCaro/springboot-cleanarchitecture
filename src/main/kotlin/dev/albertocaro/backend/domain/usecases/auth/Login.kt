package dev.albertocaro.backend.domain.usecases.auth

import dev.albertocaro.backend.core.AuthService
import dev.albertocaro.backend.data.UserRepository
import kotlin.jvm.optionals.getOrNull

class Login(
    private val authService: AuthService,
    private val repository: UserRepository,
) {

    fun execute(username: String, password: String): String? {
        val user = repository.findByUsername(username).getOrNull() ?: return null

        return authService.generateKey(user, password)
    }
}