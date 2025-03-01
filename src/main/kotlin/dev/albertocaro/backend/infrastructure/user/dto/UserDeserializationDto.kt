package dev.albertocaro.backend.infrastructure.user.dto

import dev.albertocaro.backend.infrastructure.user.validation.UniqueEmail
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@UniqueEmail
data class UserDeserializationDto(
    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    val username: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 6, max = 255)
    val password: String?,

    @field:Email
    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    val email: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    val name: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    val lastName: String?,
)