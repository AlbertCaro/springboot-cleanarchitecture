package dev.albertocaro.backend.infrastructure.user

import dev.albertocaro.backend.domain.models.toDomain
import dev.albertocaro.backend.domain.usecases.user.*
import dev.albertocaro.backend.infrastructure.user.dto.UserSerializationDto
import dev.albertocaro.backend.infrastructure.user.dto.UserDeserializationDto
import dev.albertocaro.backend.infrastructure.user.dto.toSerialization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
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
@Tag(name = "Usuarios", description = "Módulo de gestión de usuarios del sistema")
@SecurityRequirement(name = "bearerAuth")
class UserController(
    private val getUsers: GetUsers,
    private val getUserById: GetUserById,
    private val createUser: CreateUser,
    private val editUser: EditUser,
    private val deleteUser: DeleteUser,
) {

    @GetMapping("/{id}")
    @Operation(
        summary = "Consulta de usuario",
        description = "Consulta la información de un sólo registro de usuario."
    )
    fun getUser(@PathVariable id: Long): ResponseEntity<UserSerializationDto> {
        val user = getUserById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user.toSerialization())
    }

    @GetMapping
    @Operation(
        summary = "Consulta del listado de usuarios.",
        description = "Consulta la información todos los usuarios registrados."
    )
    fun getAllUsers(): ResponseEntity<List<UserSerializationDto>> {
        val users = getUsers().map { it.toSerialization() }

        return ResponseEntity.ok(users)
    }

    @PostMapping
    @Operation(
        summary = "Creación de usuarios",
        description = "Almacena un nuevo usuario en la base de datos."
    )
    fun createUser(@Valid @RequestBody user: UserDeserializationDto): ResponseEntity<UserSerializationDto> {
        val createdUser = createUser(user.toDomain())!!.toSerialization()

        return ResponseEntity.ok(createdUser)
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Edición de usuario",
        description = "Modifica un usuario en la base de datos."
    )
    fun updateUser(
        @PathVariable id: Long, @Valid @RequestBody user: UserDeserializationDto,
    ): ResponseEntity<UserSerializationDto> {
        val editedUser = editUser(id, user.toDomain())!!.toSerialization()

        return ResponseEntity.ok(editedUser)
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminación de usuario",
        description = "Remueve el registro de un usuario de la base de datos."
    )
    fun removeUser(@PathVariable id: Long): ResponseEntity<Void> {
        val user = getUserById(id) ?: return ResponseEntity.notFound().build()

        deleteUser(user)
        return ResponseEntity.noContent().build()
    }
}