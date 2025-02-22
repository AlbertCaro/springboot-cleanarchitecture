package dev.albertocaro.backend.infrastructure.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(exception: MethodArgumentNotValidException): Map<String, Any> {
        val errors = mutableMapOf<String, String>()

        exception.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage ?: "Error desconocido"
        }

        return mapOf("message" to "Errores en la información", "fields" to errors)
    }
}