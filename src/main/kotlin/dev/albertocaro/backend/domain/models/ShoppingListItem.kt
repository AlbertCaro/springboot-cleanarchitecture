package dev.albertocaro.backend.domain.models

import dev.albertocaro.backend.data.database.entity.ShoppingListItemEntity
import dev.albertocaro.backend.infrastructure.shoppingList.dto.ItemCreationDto
import dev.albertocaro.backend.infrastructure.shoppingList.dto.ItemEditionDto

data class ShoppingListItem(
    var id: Long? = null,

    var product: Product?,

    var quantity: Int = 1,

    var bought: Boolean = false,

    var list: ShoppingList? = null
)

fun ShoppingListItemEntity.toDomain() = ShoppingListItem(id, product.toDomain(), quantity, bought)

fun ItemCreationDto.toDomain() = ShoppingListItem(null, Product(product, ""), quantity!!, bought!!)

fun ItemEditionDto.toDomain() = ShoppingListItem(null, null, quantity!!, bought!!)