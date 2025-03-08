package dev.albertocaro.backend.infrastructure.auth

import dev.albertocaro.backend.domain.usecases.auth.GetAuthenticatedUser
import dev.albertocaro.backend.domain.usecases.auth.Login
import dev.albertocaro.backend.infrastructure.auth.dto.LoginDto
import dev.albertocaro.backend.infrastructure.auth.dto.ProfileDto
import dev.albertocaro.backend.infrastructure.auth.dto.TokenDto
import dev.albertocaro.backend.infrastructure.auth.dto.toProfile
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Tag(name = "Autorización", description = "Módulo de autenticación del sistema")
class AuthController(
    private val login: Login,
    private val getAuthenticatedUser: GetAuthenticatedUser,
) {

    @PostMapping("/login")
    @Operation(
        summary = "Login del sistema",
        description = "Recibe el usuario y contraseña, si las credenciales son válidas recibes un token JWT.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Token generado exitosamente",
                content = [Content(schema = Schema(implementation = TokenDto::class))]
            )
        ]
    )
    fun executeLogin(@RequestBody loginDto: LoginDto): ResponseEntity<out Any> {
        val token = login(loginDto.username, loginDto.password)

        if (token != null) {
            return ResponseEntity.ok(TokenDto(token))
        }

        return ResponseEntity.status(401).body(mapOf("error" to "Credenciales inválidas"))
    }

    @GetMapping("/profile")
    @Operation(
        summary = "Endpoint de consulta del usuario logueado",
        description = "Con este endpoint ya habiendo iniciado sesión podrás consultar tu información de usuario",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Consulta del usuario",
                content = [Content(schema = Schema(implementation = ProfileDto::class))]
            )
        ]
    )
    fun profile(): ResponseEntity<out Any> {
        val user = getAuthenticatedUser() ?: return ResponseEntity.status(500).body(mapOf("message" to "Algo salió mal."))

        return ResponseEntity.ok(user.toProfile())
    }
}