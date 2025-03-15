package dev.albertocaro.backend.domain.usecases.shoppingList

import dev.albertocaro.backend.data.ShoppingListRepository
import dev.albertocaro.backend.domain.models.ShoppingList
import dev.albertocaro.backend.domain.usecases.auth.GetAuthenticatedUser

class GetShoppingListsFromUser(
    private val repository: ShoppingListRepository,
    private val getAuthenticatedUser: GetAuthenticatedUser,
) {

    operator fun invoke(): List<ShoppingList> {
        val user = getAuthenticatedUser()

        return repository.findByUser(user!!)
    }
}