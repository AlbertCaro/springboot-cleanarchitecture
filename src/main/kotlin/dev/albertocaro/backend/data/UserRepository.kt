package dev.albertocaro.backend.data

import dev.albertocaro.backend.data.database.entity.toEntity
import dev.albertocaro.backend.domain.models.User
import dev.albertocaro.backend.domain.models.toDomain
import dev.albertocaro.backend.data.database.repository.JpaUserRepository as JpaUserRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserRepository(
    private val jpaRepository: JpaUserRepository,
) {
    fun findByEmail(email: String): User? {
        val user = jpaRepository.findByEmail(email)

        return user?.toDomain()
    }

    fun findByUsername(username: String): User? {
        val user = jpaRepository.findByUsername(username)

        return user?.toDomain()
    }

    fun findById(id: Long): User? {
        val user = jpaRepository.findById(id).getOrNull()

        return user?.toDomain()
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