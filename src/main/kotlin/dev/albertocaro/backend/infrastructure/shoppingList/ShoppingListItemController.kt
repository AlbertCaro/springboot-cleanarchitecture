package dev.albertocaro.backend.infrastructure.shoppingList

import dev.albertocaro.backend.domain.models.toDomain
import dev.albertocaro.backend.domain.usecases.shoppingList.item.DeleteItem
import dev.albertocaro.backend.domain.usecases.shoppingList.item.EditItem
import dev.albertocaro.backend.domain.usecases.shoppingList.item.GetItemById
import dev.albertocaro.backend.infrastructure.shoppingList.dto.ItemEditionDto
import dev.albertocaro.backend.infrastructure.shoppingList.dto.ItemSerializationDto
import dev.albertocaro.backend.infrastructure.shoppingList.dto.toSerialization
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shopping_lists/items")
class ShoppingListItemController(
    private val getItemById: GetItemById,
    private val editItem: EditItem,
    private val deleteItem: DeleteItem,
) {
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseEntity<ItemSerializationDto> {
        val shoppingList = getItemById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(shoppingList.toSerialization())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long, @Valid @RequestBody item: ItemEditionDto,
    ): ResponseEntity<ItemSerializationDto> {
        val editedList = editItem(id, item.toDomain()) ?: return ResponseEntity.notFound().build()

        val editedShoppingList = editedList.toSerialization()

        return ResponseEntity.ok(editedShoppingList)
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long): ResponseEntity<Void> {
        deleteItem(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.noContent().build()
    }
}