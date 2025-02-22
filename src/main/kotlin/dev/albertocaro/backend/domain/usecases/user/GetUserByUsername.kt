package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User
import java.util.*

class GetUserByUsername(
    private var repository: UserRepository
) {

    operator fun invoke(username: String): Optional<User> {
        return repository.findByUsername(username)
    }
}