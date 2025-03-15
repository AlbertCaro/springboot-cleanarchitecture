package dev.albertocaro.backend.data.database.entity

import dev.albertocaro.backend.domain.models.ShoppingListItem
import jakarta.persistence.*

@Entity
@Table(name = "shopping_list_items")
data class ShoppingListItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "shopping_list_id", nullable = false)
    val shoppingList: ShoppingListEntity,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,

    @Column(nullable = false)
    val quantity: Int = 1,

    @Column(nullable = false)
    val bought: Boolean = false
)

fun ShoppingListItem.toEntity() = ShoppingListItemEntity(id, list!!.toEntity(), product!!.toEntity(), quantity, bought)