package dev.albertocaro.backend.infrastructure.shoppingList

import dev.albertocaro.backend.domain.models.toDomain
import dev.albertocaro.backend.domain.usecases.shoppingList.*
import dev.albertocaro.backend.infrastructure.shoppingList.dto.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shopping_lists")
class ShoppingListController(
    private val createShoppingList: CreateShoppingList,
    private val getShoppingListById: GetShoppingListById,
    private val getShoppingListsFromUser: GetShoppingListsFromUser,
    private val editShippingList: EditShoppingList,
    private val deleteShippingList: DeleteShippingList,
    private val addItemToShoppingList: AddItemToShoppingList,
) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseEntity<ShoppingListSerializationDto> {
        val shoppingList = getShoppingListById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(shoppingList.toSerialization())
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<ShoppingListSerializationDto>> {
        val shoppingLists = getShoppingListsFromUser().map { it.toSerialization() }

        return ResponseEntity.ok(shoppingLists)
    }

    @PostMapping
    fun create(@Valid @RequestBody shoppingList: ShoppingListDeserializationDto): ResponseEntity<ShoppingListSerializationDto> {
        val createdShoppingList = createShoppingList(shoppingList.toDomain())!!

        return ResponseEntity.ok(createdShoppingList.toSerialization())
    }

    @PostMapping("/{id}/items")
    fun addItem(@PathVariable id: Long, @Valid @RequestBody item: ItemCreationDto): ResponseEntity<ItemSerializationDto> {
        val createdItem = addItemToShoppingList(id, item.toDomain()) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(createdItem.toSerialization())
    }

    @GetMapping("/{id}/items")
    fun getItems(@PathVariable id: Long): ResponseEntity<List<ItemSerializationDto>> {
        val list = getShoppingListById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(list.items.map { it.toSerialization() })
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long, @Valid @RequestBody shoppingList: ShoppingListDeserializationDto,
    ): ResponseEntity<ShoppingListSerializationDto> {
        val editedList = editShippingList(id, shoppingList.toDomain()) ?: return ResponseEntity.notFound().build()

        val editedShoppingList = editedList.toSerialization()

        return ResponseEntity.ok(editedShoppingList)
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long): ResponseEntity<Void> {
        deleteShippingList(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.noContent().build()
    }
}