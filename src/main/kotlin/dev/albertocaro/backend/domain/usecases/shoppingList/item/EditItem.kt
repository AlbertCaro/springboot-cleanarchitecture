package dev.albertocaro.backend.domain.usecases.shoppingList.item

import dev.albertocaro.backend.data.ShoppingListItemRepository
import dev.albertocaro.backend.domain.models.ShoppingListItem

class EditItem(
    private val repository: ShoppingListItemRepository,
    private val getItemById: GetItemById,
) {

    operator fun invoke(id: Long, item: ShoppingListItem): ShoppingListItem? {
        val storedItem = getItemById(id) ?: return null

        storedItem.apply {
            quantity = item.quantity
            bought = item.bought
        }

        return repository.save(item)
    }
}