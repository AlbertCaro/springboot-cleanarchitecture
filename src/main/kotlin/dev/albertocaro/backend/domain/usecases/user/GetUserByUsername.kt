package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User

class GetUserByUsername(
    private var repository: UserRepository
) {

    operator fun invoke(username: String): User? {
        return repository.findByUsername(username)
    }
}