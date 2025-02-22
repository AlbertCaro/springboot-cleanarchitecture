package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User

class GetUsers(
    private val repository: UserRepository
) {

    operator fun invoke(): List<User> {
        return repository.findAll()
    }
}