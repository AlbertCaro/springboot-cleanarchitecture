package dev.albertocaro.backend.data.database.entity

import dev.albertocaro.backend.domain.models.Product
import jakarta.persistence.*

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 150)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    val category: CategoryEntity? = null
)

fun Product.toEntity() = ProductEntity(id, name)