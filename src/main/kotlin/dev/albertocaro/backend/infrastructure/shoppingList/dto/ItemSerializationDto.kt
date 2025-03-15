package dev.albertocaro.backend.infrastructure.shoppingList.dto

import dev.albertocaro.backend.domain.models.ShoppingListItem
import dev.albertocaro.backend.infrastructure.product.dto.ProductSerializationDto
import dev.albertocaro.backend.infrastructure.product.dto.toSerialization

data class ItemSerializationDto(
    val id: Long,

    val quantity: Int,

    val bought: Boolean,

    val product: ProductSerializationDto,
)

fun ShoppingListItem.toSerialization() = ItemSerializationDto(id!!, quantity, bought, product!!.toSerialization())