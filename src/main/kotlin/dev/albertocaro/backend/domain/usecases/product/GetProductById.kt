package dev.albertocaro.backend.domain.usecases.product

import dev.albertocaro.backend.data.ProductRepository
import dev.albertocaro.backend.domain.models.Product

class GetProductById(
    private val repository: ProductRepository
) {

    operator fun invoke(id: Long): Product? {
        return repository.findById(id)
    }
}