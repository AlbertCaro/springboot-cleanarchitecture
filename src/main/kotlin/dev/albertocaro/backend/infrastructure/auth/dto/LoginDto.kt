package dev.albertocaro.backend.infrastructure.auth.dto

data class LoginDto(
    val username: String,
    val password: String,
)