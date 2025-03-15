package dev.albertocaro.backend.infrastructure.auth

import dev.albertocaro.backend.infrastructure.auth.dto.LoginDto
import dev.albertocaro.backend.infrastructure.user.dto.UserDeserializationDto
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationTest @Autowired constructor(
    private val testRestTemplate: TestRestTemplate,
) {
    class EndpointsWithAuthenticationRequiredProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of("/api/users", HttpMethod.GET, UserDeserializationDto::class.java),
                Arguments.of("/api/users", HttpMethod.POST, UserDeserializationDto::class.java),
                Arguments.of("/api/users/1", HttpMethod.PUT, UserDeserializationDto::class.java),
                Arguments.of("/api/users/1", HttpMethod.DELETE, UserDeserializationDto::class.java),
            )
        }
    }

    class EndpointsWithoutAuthenticationRequiredProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(
                    "/api/auth/login",
                    HttpMethod.POST,
                    LoginDto::class.java,
                    LoginDto("alberto.caro", "picapapas")
                ),
            )
        }
    }


    @ParameterizedTest
    @ArgumentsSource(EndpointsWithAuthenticationRequiredProvider::class)
    fun <S> `should not can access to endpoint without authentication`(
        endpoint: String,
        method: HttpMethod,
        serializationObj: Class<S>
    ) {
        var response: ResponseEntity<S>? = null

        when (method) {
            HttpMethod.GET -> {
                response = testRestTemplate.getForEntity(endpoint, serializationObj)
            }

            HttpMethod.POST -> {
                response = testRestTemplate.postForEntity(endpoint, null, serializationObj)
            }

            HttpMethod.PUT -> {
                response = testRestTemplate.exchange(endpoint, method, null, serializationObj)
            }

            HttpMethod.DELETE -> {
                response = testRestTemplate.exchange(endpoint, method, null, serializationObj)
            }
        }

        assertEquals(HttpStatus.UNAUTHORIZED, response!!.statusCode)
    }

    @ParameterizedTest
    @ArgumentsSource(EndpointsWithoutAuthenticationRequiredProvider::class)
    fun <S> `should can access to endpoint without authentication`(
        endpoint: String,
        method: HttpMethod,
        serializationObj: Class<S>,
        body: Any
    ) {
        var response: ResponseEntity<S>? = null

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val request = HttpEntity(body, headers)

        when (method) {
            HttpMethod.GET -> {
                response = testRestTemplate.getForEntity(endpoint, serializationObj)
            }

            HttpMethod.POST -> {
                response = testRestTemplate.postForEntity(endpoint, request, serializationObj)
            }

            HttpMethod.PUT -> {
                response = testRestTemplate.exchange(endpoint, method, request, serializationObj)
            }

            HttpMethod.DELETE -> {
                response = testRestTemplate.exchange(endpoint, method, null, serializationObj)
            }
        }

        assertNotEquals(HttpStatus.UNAUTHORIZED, response!!.statusCode)
    }
}