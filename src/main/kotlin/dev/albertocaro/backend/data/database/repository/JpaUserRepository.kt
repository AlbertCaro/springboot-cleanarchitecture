package dev.albertocaro.backend.data.database.repository

import dev.albertocaro.backend.data.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface JpaUserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): Optional<UserEntity>
}