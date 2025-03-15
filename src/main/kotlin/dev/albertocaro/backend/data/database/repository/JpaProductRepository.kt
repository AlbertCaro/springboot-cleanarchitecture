package dev.albertocaro.backend.data.database.repository

import dev.albertocaro.backend.data.database.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaProductRepository: JpaRepository<ProductEntity, Long> {
}