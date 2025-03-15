package dev.albertocaro.backend.domain.models

import dev.albertocaro.backend.data.database.entity.ShoppingListEntity
import dev.albertocaro.backend.infrastructure.shoppingList.dto.ShoppingListDeserializationDto
import java.util.*

data class ShoppingList(
    var id: Long? = null,

    var name: String,

    val createdAt: Date = Date(),

    var user: User? = null,

    val items: List<ShoppingListItem> = mutableListOf()
)

fun ShoppingListDeserializationDto.toDomain() = ShoppingList(null, name)

fun ShoppingListEntity.toDomain() = ShoppingList(id, name, createdAt, user?.toDomain(), items.map { it.toDomain() })