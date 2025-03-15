package dev.albertocaro.backend.di

import dev.albertocaro.backend.data.ShoppingListItemRepository
import dev.albertocaro.backend.data.ShoppingListRepository
import dev.albertocaro.backend.domain.usecases.auth.GetAuthenticatedUser
import dev.albertocaro.backend.domain.usecases.product.GetProductById
import dev.albertocaro.backend.domain.usecases.shoppingList.*
import dev.albertocaro.backend.domain.usecases.shoppingList.item.DeleteItem
import dev.albertocaro.backend.domain.usecases.shoppingList.item.EditItem
import dev.albertocaro.backend.domain.usecases.shoppingList.item.GetItemById
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ShoppingListConfig {

    @Bean
    fun createShoppingList(
        repository: ShoppingListRepository,
        getAuthenticatedUser: GetAuthenticatedUser,
        getShoppingListById: GetShoppingListById,
    ) = CreateShoppingList(repository, getAuthenticatedUser, getShoppingListById)

    @Bean
    fun getShoppingListById(repository: ShoppingListRepository) = GetShoppingListById(repository)

    @Bean
    fun getShippingListsFromUser(repository: ShoppingListRepository, getAuthenticatedUser: GetAuthenticatedUser) =
        GetShoppingListsFromUser(repository, getAuthenticatedUser)

    @Bean
    fun editShippingList(repository: ShoppingListRepository, getShoppingListById: GetShoppingListById) =
        EditShoppingList(repository, getShoppingListById)

    @Bean
    fun deleteShippingList(repository: ShoppingListRepository, getShoppingListById: GetShoppingListById) =
        DeleteShippingList(repository, getShoppingListById)

    @Bean
    fun addItemToShoppingList(
        repository: ShoppingListItemRepository,
        getShoppingListById: GetShoppingListById,
        getProductById: GetProductById,
    ) = AddItemToShoppingList(repository, getShoppingListById, getProductById)

    @Bean
    fun deleteItem(repository: ShoppingListItemRepository, getItemById: GetItemById) = DeleteItem(repository, getItemById)

    @Bean
    fun editItem(repository: ShoppingListItemRepository, getItemById: GetItemById) = EditItem(repository, getItemById)

    @Bean
    fun getItemById(repository: ShoppingListItemRepository) = GetItemById(repository)
}