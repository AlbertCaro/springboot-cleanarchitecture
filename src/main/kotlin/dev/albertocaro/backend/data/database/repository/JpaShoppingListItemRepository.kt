package dev.albertocaro.backend.data.database.repository

import dev.albertocaro.backend.data.database.entity.ShoppingListItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaShoppingListItemRepository : JpaRepository<ShoppingListItemEntity, Long> {
}