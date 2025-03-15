package dev.albertocaro.backend.infrastructure.auth.dto

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class LoginDto(
    @field:NotNull
    @field:NotBlank
    val username: String?,
    @field:NotNull
    @field:NotBlank
    val password: String?,
)