package dev.albertocaro.backend.data

import dev.albertocaro.backend.data.database.entity.toEntity
import dev.albertocaro.backend.data.database.repository.JpaShoppingListRepository
import dev.albertocaro.backend.domain.models.ShoppingList
import dev.albertocaro.backend.domain.models.User
import dev.albertocaro.backend.domain.models.toDomain
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class ShoppingListRepository(
    private val jpaRepository: JpaShoppingListRepository
) {
    fun findByUser(user: User): List<ShoppingList> {
        val shoppingList = jpaRepository.findByUserId(user.id!!)

        return shoppingList.map { it.toDomain() }
    }

    fun findById(id: Long): ShoppingList? {
        val shoppingList = jpaRepository.findById(id).getOrNull()

        return shoppingList?.toDomain()
    }

    fun findAll(): List<ShoppingList> {
        return jpaRepository.findAll().map { it.toDomain() }
    }

    fun save(shoppingList: ShoppingList): ShoppingList {
        return jpaRepository.save(shoppingList.toEntity()).toDomain()
    }

    fun delete(shoppingList: ShoppingList) {
        jpaRepository.delete(shoppingList.toEntity())
    }
}