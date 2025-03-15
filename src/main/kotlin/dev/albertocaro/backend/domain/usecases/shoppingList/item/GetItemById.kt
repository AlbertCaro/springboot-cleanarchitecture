package dev.albertocaro.backend.domain.usecases.shoppingList.item

import dev.albertocaro.backend.data.ShoppingListItemRepository
import dev.albertocaro.backend.domain.models.ShoppingListItem

class GetItemById(
    private val repository: ShoppingListItemRepository
) {

    operator fun invoke(id: Long): ShoppingListItem? {
        return repository.findById(id)
    }
}