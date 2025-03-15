package dev.albertocaro.backend.domain.usecases.shoppingList

import dev.albertocaro.backend.data.ShoppingListRepository
import dev.albertocaro.backend.domain.models.ShoppingList

class EditShoppingList(
    private val repository: ShoppingListRepository,
    private val getShoppingListById: GetShoppingListById,
) {

    operator fun invoke(id: Long, shippingList: ShoppingList): ShoppingList? {
        val storedShoppingList = getShoppingListById(id) ?: return null

        storedShoppingList.name = shippingList.name

        repository.save(storedShoppingList)

        return getShoppingListById(id)
    }
}