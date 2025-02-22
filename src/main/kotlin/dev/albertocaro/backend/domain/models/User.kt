package dev.albertocaro.backend.domain.models

import dev.albertocaro.backend.data.database.entity.UserEntity
import dev.albertocaro.backend.infrastructure.user.dto.UserModificationDto


data class User(
    var id: Long? = null,

    val username: String,

    var password: String,

    val email: String,

    val name: String,

    val lastName: String,
)

fun UserEntity.toDomain() = User(id, username, password, email, name, lastName)

fun UserModificationDto.toDomain() = User(null, username!!, password!!, email!!, name!!, lastName!!)