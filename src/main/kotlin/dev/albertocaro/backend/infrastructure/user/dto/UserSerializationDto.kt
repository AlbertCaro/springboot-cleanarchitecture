package dev.albertocaro.backend.infrastructure.user.dto

import dev.albertocaro.backend.domain.models.User

data class UserSerializationDto(
    val id: Long? = null,

    val username: String,

    val email: String,

    val name: String,

    val lastName: String,
)

fun User.toSerialization() = UserSerializationDto(id, username, email, name, lastName)