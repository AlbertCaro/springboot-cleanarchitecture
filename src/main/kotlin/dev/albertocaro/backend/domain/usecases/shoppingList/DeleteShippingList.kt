package dev.albertocaro.backend.domain.usecases.shoppingList

import dev.albertocaro.backend.data.ShoppingListRepository

class DeleteShippingList(
    private val repository: ShoppingListRepository,
    private val getShoppingListById: GetShoppingListById
) {

    operator fun invoke(id: Long): Boolean? {
        val list = getShoppingListById(id) ?: return null

        repository.delete(list)

        return true
    }
}