package dev.albertocaro.backend.data.database.repository

import dev.albertocaro.backend.data.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?

    fun findByUsername(username: String): UserEntity?
}