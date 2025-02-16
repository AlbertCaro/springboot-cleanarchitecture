package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User

class DeleteUser(
    private val repository: UserRepository
) {

    fun execute(user: User) {
        repository.delete(user)
    }
}