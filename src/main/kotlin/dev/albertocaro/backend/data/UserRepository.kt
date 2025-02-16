package dev.albertocaro.backend.data

import dev.albertocaro.backend.data.database.entity.toEntity
import dev.albertocaro.backend.domain.models.User
import dev.albertocaro.backend.domain.models.toDomain
import dev.albertocaro.backend.data.database.repository.JpaUserRepository as JpaUserRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class UserRepository(
    private val jpaRepository: JpaUserRepository
) {
    fun findByUsername(username: String): Optional<User> {
        return jpaRepository.findByUsername(username).map { it.toDomain() }
    }

    fun findById(id: Long): Optional<User> {
        return jpaRepository.findById(id).map { it.toDomain() }
    }

    fun findAll(): List<User> {
        return jpaRepository.findAll().map { it.toDomain() }
    }

    fun saveUser(user: User) {
        jpaRepository.save(user.toEntity())
    }

    fun delete(user: User) {
        jpaRepository.delete(user.toEntity())
    }
}