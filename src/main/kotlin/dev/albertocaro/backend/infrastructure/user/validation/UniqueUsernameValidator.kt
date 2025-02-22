package dev.albertocaro.backend.infrastructure.user.validation

import dev.albertocaro.backend.domain.usecases.user.GetUserByUsername
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class UniqueUsernameValidator(
    private val getUserByUsername: GetUserByUsername
) : ConstraintValidator<UniqueUsername, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank() || value.isEmpty()) return true

        val user = getUserByUsername(value)

        return !user.isPresent
    }
}