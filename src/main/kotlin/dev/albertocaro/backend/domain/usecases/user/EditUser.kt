package dev.albertocaro.backend.domain.usecases.user

import dev.albertocaro.backend.core.PasswordEncoderService
import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.models.User

class EditUser(
    private val repository: UserRepository,
    private val getUserById: GetUserById,
    private val passwordEncoderService: PasswordEncoderService
) {

    operator fun invoke(id: Long,user: User): User? {
        user.id = id
        user.password = passwordEncoderService.encode(user.password)!!

        repository.saveUser(user)

        return getUserById(user.id!!)
    }
}