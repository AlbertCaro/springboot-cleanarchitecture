package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User

class GetUserById(
    private var repository: UserRepository
) {

    operator fun invoke(id: Long): User? {
        return repository.findById(id)
    }
}