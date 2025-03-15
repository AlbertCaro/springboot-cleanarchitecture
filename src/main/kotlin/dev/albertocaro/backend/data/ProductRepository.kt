package dev.albertocaro.backend.data

import dev.albertocaro.backend.data.database.entity.ProductEntity
import dev.albertocaro.backend.data.database.entity.toEntity
import dev.albertocaro.backend.data.database.repository.JpaProductRepository
import dev.albertocaro.backend.domain.models.Product
import dev.albertocaro.backend.domain.models.toDomain
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class ProductRepository(
    private val jpaRepository: JpaProductRepository
) {

    fun findById(id: Long): Product? {
        return jpaRepository.findById(id).getOrNull()?.toDomain()
    }

    fun save(product: Product): ProductEntity {
        return jpaRepository.save(product.toEntity())
    }

    fun delete(product: Product) {
        jpaRepository.delete(product.toEntity())
    }
}