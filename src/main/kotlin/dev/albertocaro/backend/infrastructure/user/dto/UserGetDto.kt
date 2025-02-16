package dev.albertocaro.backend.infrastructure.user.dto

import dev.albertocaro.backend.domain.models.User

data class UserGetDto(
    val id: Long? = null,

    val username: String,

    val email: String,

    val name: String,

    val lastName: String,
)

fun User.toGet() = UserGetDto(id, username, email, name, lastName)