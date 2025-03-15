package dev.albertocaro.backend.infrastructure.product.dto

import dev.albertocaro.backend.domain.models.Product

data class ProductSerializationDto(
    val id: Long,
    val name: String,
)

fun Product.toSerialization() = ProductSerializationDto(id!!, name)