package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User

class GetUserByEmail(
    private var repository: UserRepository
) {

    operator fun invoke(email: String): User? {
        return repository.findByEmail(email)
    }
}