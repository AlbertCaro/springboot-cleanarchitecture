package dev.albertocaro.backend.infrastructure.shoppingList.dto

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class ShoppingListDeserializationDto(
    @field:NotNull
    @field:NotBlank
    val name: String,
)
