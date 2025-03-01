package dev.albertocaro.backend.di

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI {

        val contact = Contact().apply {
            name = "Alberto Caro"
            email = "albertcaronava@gmail.com"
            url = "https://albertocaro.dev"
        }

        val info = Info().apply {
            title = "API Shopping List"
            version = "1.0"
            description = "Documentación de la API con Swagger"
            contact(contact)
        }

        return OpenAPI().info(info)
    }
}