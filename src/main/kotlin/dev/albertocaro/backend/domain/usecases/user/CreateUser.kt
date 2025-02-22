package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.core.PasswordEncoderService
import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User
import java.util.*

class CreateUser(
    private val repository: UserRepository,
    private val passwordEncoderService: PasswordEncoderService,
) {

    operator fun invoke(user: User): Optional<User> {
        user.password = passwordEncoderService.encode(user.password)!!

        repository.saveUser(user)

        return repository.findByUsername(user.username)
    }
}