package dev.albertocaro.backend.data

import dev.albertocaro.backend.data.database.entity.toEntity
import dev.albertocaro.backend.data.database.repository.JpaShoppingListItemRepository
import dev.albertocaro.backend.domain.models.ShoppingListItem
import dev.albertocaro.backend.domain.models.toDomain
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class ShoppingListItemRepository(
    private val jpaRepository: JpaShoppingListItemRepository
) {

    fun save(item: ShoppingListItem): ShoppingListItem {
        return jpaRepository.save(item.toEntity()).toDomain()
    }

    fun delete(item: ShoppingListItem) {
        jpaRepository.delete(item.toEntity())
    }

    fun findById(id: Long): ShoppingListItem? {
        return jpaRepository.findById(id).getOrNull()?.toDomain()
    }
}