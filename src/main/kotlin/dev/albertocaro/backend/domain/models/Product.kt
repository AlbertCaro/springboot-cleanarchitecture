package dev.albertocaro.backend.domain.models

import dev.albertocaro.backend.data.database.entity.ProductEntity

data class Product(
    val id: Long? = null,

    val name: String,

    val category: Category? = null,
)

fun ProductEntity.toDomain() = Product(id, name, category?.toDomain())