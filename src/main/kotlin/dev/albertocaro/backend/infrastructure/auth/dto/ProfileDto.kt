package dev.albertocaro.backend.infrastructure.auth.dto

import dev.albertocaro.backend.domain.models.User

data class ProfileDto (
    val username: String,
    val email: String,
    val fullName: String,
)

fun User.toProfile() = ProfileDto(username, email, "$name $lastName")