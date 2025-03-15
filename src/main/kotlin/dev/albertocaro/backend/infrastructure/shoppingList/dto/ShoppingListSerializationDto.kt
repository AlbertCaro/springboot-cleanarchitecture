package dev.albertocaro.backend.infrastructure.shoppingList.dto

import dev.albertocaro.backend.domain.models.ShoppingList
import java.util.*

data class ShoppingListSerializationDto(
    val id: Long? = null,

    val name: String,

    val createdAt: Date = Date(),

    val items: List<ItemSerializationDto>,
)

fun ShoppingList.toSerialization() =
    ShoppingListSerializationDto(id, name, createdAt, items.map { it.toSerialization() })