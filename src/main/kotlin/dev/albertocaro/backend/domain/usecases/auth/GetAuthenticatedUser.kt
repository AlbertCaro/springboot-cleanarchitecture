package dev.albertocaro.backend.domain.usecases.auth

import dev.albertocaro.backend.core.AuthService
import dev.albertocaro.backend.domain.models.User
import dev.albertocaro.backend.domain.usecases.user.GetUserByUsername

class GetAuthenticatedUser(
    private val authService: AuthService,
    private val getUserByUsername: GetUserByUsername
) {

    operator fun invoke(): User? {
        val username = authService.getAuthenticationUsername()

        return username?.let { getUserByUsername(it) }
    }
}