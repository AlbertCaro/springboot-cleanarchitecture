package dev.albertocaro.backend.infrastructure.auth.model

data class LoginDto(
    val username: String,
    val password: String,
)