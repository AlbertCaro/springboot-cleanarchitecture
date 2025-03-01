package dev.albertocaro.backend.infrastructure.auth.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Estructura de respuesta de un token")
data class TokenDto (
    @Schema(description = "Respuesta del token", example = "token")
    val message: String
)