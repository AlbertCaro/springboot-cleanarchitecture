package dev.albertocaro.backend.domain.usecases.shoppingList.item

import dev.albertocaro.backend.data.ShoppingListItemRepository

class DeleteItem(
    private val repository: ShoppingListItemRepository,
    private val getItemById: GetItemById,
) {

    operator fun invoke(id: Long): Boolean? {
        val item = getItemById(id) ?: return null

        repository.delete(item)

        return true
    }
}