package dev.albertocaro.backend.infrastructure.shoppingList.dto

import org.jetbrains.annotations.NotNull

data class ItemEditionDto(
    @field:NotNull
    val quantity: Int? = null,

    @field:NotNull
    val bought: Boolean? = null,
)