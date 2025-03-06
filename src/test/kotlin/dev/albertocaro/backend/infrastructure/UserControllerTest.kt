package dev.albertocaro.backend.infrastructure

import dev.albertocaro.backend.JwtInterceptor
import dev.albertocaro.backend.infrastructure.user.dto.UserSerializationDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest @Autowired constructor(
    private val restTemplateBuilder: RestTemplateBuilder,
    private val jwtInterceptor: JwtInterceptor,
    @LocalServerPort private val port: Int
) {

    private lateinit var restTemplate: RestTemplate
    private lateinit var baseUrl: String

    @BeforeEach
    fun setUp() {
        baseUrl = "http://localhost:$port"
        restTemplate = restTemplateBuilder.additionalInterceptors(jwtInterceptor).build()
    }

    @Test
    fun `should get a valid user by ID`() {
        val response = restTemplate.getForEntity<UserSerializationDto>("$baseUrl/api/users/1")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(response.body!!.username, "alberto.caro")
    }

    @Test
    fun `should can get the list of users`() {
        val response = restTemplate.getForEntity<List<UserSerializationDto>>("$baseUrl/api/users")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(1, response.body!!.size)
    }
}