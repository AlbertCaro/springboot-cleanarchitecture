package dev.albertocaro.backend.domain.usecases.shoppingList

import dev.albertocaro.backend.data.ShoppingListItemRepository
import dev.albertocaro.backend.domain.models.ShoppingListItem
import dev.albertocaro.backend.domain.usecases.product.GetProductById

class AddItemToShoppingList(
    private val repository: ShoppingListItemRepository,
    private val getShoppingListById: GetShoppingListById,
    private val getProductById: GetProductById,
) {

    operator fun invoke(listId: Long, item: ShoppingListItem): ShoppingListItem? {
        val list = getShoppingListById(listId) ?: return null
        val product = getProductById(item.product?.id!!) ?: return null

        item.apply {
            this.list = list
            this.product = product
        }

        return repository.save(item)
    }
}