package dev.albertocaro.backend.data.database.entity

import dev.albertocaro.backend.domain.models.ShoppingList
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "shopping_lists")
data class ShoppingListEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(nullable = false, updatable = false)
    val createdAt: Date = Date(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity? = null,

    @OneToMany(mappedBy = "shoppingList", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: List<ShoppingListItemEntity> = mutableListOf()
)

fun ShoppingList.toEntity() = ShoppingListEntity(id, name, createdAt, user?.toEntity())