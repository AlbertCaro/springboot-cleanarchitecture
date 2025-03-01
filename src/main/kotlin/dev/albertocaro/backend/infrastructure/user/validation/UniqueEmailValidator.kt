package dev.albertocaro.backend.infrastructure.user.validation

import dev.albertocaro.backend.domain.usecases.user.GetUserByEmail
import dev.albertocaro.backend.infrastructure.common.RequestProviderService
import dev.albertocaro.backend.infrastructure.user.dto.UserDeserializationDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class UniqueEmailValidator(
    private val getUserByEmail: GetUserByEmail,
    private val requestProvider: RequestProviderService
) : ConstraintValidator<UniqueEmail, UserDeserializationDto> {

    private lateinit var message: String

    override fun initialize(constraintAnnotation: UniqueEmail) {
        message = constraintAnnotation.message
    }

    override fun isValid(value: UserDeserializationDto?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        if (value.email.isNullOrBlank() || value.email.isEmpty()) return true

        val user = getUserByEmail(value.email)

        val method = requestProvider.getRequest()?.method

        if (user == null) {
            return true
        }

        if (value.username == user.username && method == "PUT") {
            return true
        }


        val message = this.message.replace("{email}", value.email)

        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(message)
            .addPropertyNode("email")
            .addConstraintViolation()

        return false
    }
}