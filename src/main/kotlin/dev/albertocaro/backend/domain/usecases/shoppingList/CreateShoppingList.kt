package dev.albertocaro.backend.domain.usecases.shoppingList

import dev.albertocaro.backend.data.ShoppingListRepository
import dev.albertocaro.backend.domain.models.ShoppingList
import dev.albertocaro.backend.domain.usecases.auth.GetAuthenticatedUser

class CreateShoppingList(
    private val repository: ShoppingListRepository,
    private val getAuthenticatedUser: GetAuthenticatedUser,
    private val getShoppingListById: GetShoppingListById,
) {

    operator fun invoke(shoppingList: ShoppingList): ShoppingList? {
        val user = getAuthenticatedUser()

        shoppingList.user = user

        val createdList = repository.save(shoppingList)

        return getShoppingListById(createdList.id!!)
    }
}