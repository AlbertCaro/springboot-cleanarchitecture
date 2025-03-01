package dev.albertocaro.backend.infrastructure.auth

import dev.albertocaro.backend.domain.usecases.auth.Login
import dev.albertocaro.backend.infrastructure.auth.dto.LoginDto
import dev.albertocaro.backend.infrastructure.auth.dto.TokenDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Tag(name = "Autorización", description = "Módulo de autenticación del sistema")
class AuthController(
    private val login: Login
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
}