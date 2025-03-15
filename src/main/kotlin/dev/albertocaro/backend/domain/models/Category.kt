package dev.albertocaro.backend.domain.models

import dev.albertocaro.backend.data.database.entity.CategoryEntity

data class Category(
    val id: Long? = null,

    val name: String
)

fun CategoryEntity.toDomain() = Category(id, name)

