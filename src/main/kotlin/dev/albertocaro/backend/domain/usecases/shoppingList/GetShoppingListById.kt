package dev.albertocaro.backend.domain.usecases.shoppingList

import dev.albertocaro.backend.data.ShoppingListRepository
import dev.albertocaro.backend.domain.models.ShoppingList

class GetShoppingListById(
    private val repository: ShoppingListRepository,
) {

    operator fun invoke(id: Long): ShoppingList? {
        return repository.findById(id)
    }
}