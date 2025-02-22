package dev.albertocaro.backend.infrastructure.user.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [UniqueUsernameValidator::class])
annotation class UniqueUsername(
    val message: String = "El nombre de usuario ya existe",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
