package dev.albertocaro.backend.data.database.repository

import dev.albertocaro.backend.data.database.entity.ShoppingListEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaShoppingListRepository : JpaRepository<ShoppingListEntity, Long> {
    fun findByUserId(userId: Long): List<ShoppingListEntity>
}