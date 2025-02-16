package dev.albertocaro.backend.infrastructure.user.dto

data class UserModificationDto (
    val username: String,

    val password: String,

    val email: String,

    val name: String,

    val lastName: String,
)