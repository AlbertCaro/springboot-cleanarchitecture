package dev.albertocaro.backend.di

import dev.albertocaro.backend.data.ProductRepository
import dev.albertocaro.backend.domain.usecases.product.GetProductById
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductConfig {

    @Bean
    fun getProductById(repository: ProductRepository) = GetProductById(repository)
}