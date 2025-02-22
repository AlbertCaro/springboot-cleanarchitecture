package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User
import java.util.*

class GetUserById(
    private var repository: UserRepository
) {

    operator fun invoke(id: Long): Optional<User> {
        return repository.findById(id)
    }
}