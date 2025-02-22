package dev.albertocaro.backend.infrastructure.user

import dev.albertocaro.backend.domain.models.toDomain
import dev.albertocaro.backend.domain.usecases.user.*
import dev.albertocaro.backend.infrastructure.user.dto.UserGetDto
import dev.albertocaro.backend.infrastructure.user.dto.UserModificationDto
import dev.albertocaro.backend.infrastructure.user.dto.toGet
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("/users")
class UserController(
) {
    @Autowired
    lateinit var getUsers: GetUsers

    @Autowired
    lateinit var getUserById: GetUserById

    @Autowired
    lateinit var createUser: CreateUser

    @Autowired
    lateinit var editUser: EditUser

    @Autowired
    lateinit var deleteUser: DeleteUser

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserGetDto> {
        val user = getUserById(id)

        return user.map { ResponseEntity.ok(it.toGet()) }.orElseGet { ResponseEntity.notFound().build() }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserGetDto>> {
        val users = getUsers().map { it.toGet() }

        return ResponseEntity.ok(users)
    }

    @PostMapping
    fun createUser(@Valid @RequestBody user: UserModificationDto): ResponseEntity<Optional<UserGetDto>> {
        val createdUser = createUser(user.toDomain()).map { it.toGet() }

        return ResponseEntity.ok(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long, @Valid @RequestBody user: UserModificationDto
    ): ResponseEntity<Optional<UserGetDto>> {
        val editedUser = editUser(id, user.toDomain()).map { it.toGet() }

        return ResponseEntity.ok(editedUser)
    }

    @DeleteMapping("/{id}")
    fun removeUser(@PathVariable id: Long): ResponseEntity<Void> {
        val user = getUserById(id).getOrNull() ?: return ResponseEntity.notFound().build()

        deleteUser(user)
        return ResponseEntity.noContent().build()
    }
}