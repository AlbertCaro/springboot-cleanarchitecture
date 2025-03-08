package dev.albertocaro.backend.infrastructure

import dev.albertocaro.backend.JwtInterceptor
import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.infrastructure.user.dto.UserDeserializationDto
import dev.albertocaro.backend.infrastructure.user.dto.UserSerializationDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = ["classpath:test/clean-db-after-test.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerTest @Autowired constructor(
    private val restTemplateBuilder: RestTemplateBuilder,
    private val jwtInterceptor: JwtInterceptor,
    @LocalServerPort private val port: Int,
) {

    private lateinit var restTemplate: RestTemplate
    private lateinit var moduleUrl: String

    @BeforeEach
    fun setUp() {
        moduleUrl = "http://localhost:$port/api/users"
        restTemplate = restTemplateBuilder.additionalInterceptors(jwtInterceptor).build()
    }

    @Test
    fun `should get a valid user by ID`() {
        val response = restTemplate.getForEntity<UserSerializationDto>("$moduleUrl/1")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(response.body!!.username, "alberto.caro")
    }

    @Test
    fun `should can get the list of users`() {
        val response = restTemplate.getForEntity<List<UserSerializationDto>>(moduleUrl)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(2, response.body!!.size)
    }

    @Test
    fun `should can create a new user`() {
        val newUser = UserDeserializationDto("john.doe", "hola123", "test@gmail.com", "John", "Doe")

        val response =
            restTemplate.postForEntity(moduleUrl, newUser, UserSerializationDto::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(response.body!!.name, newUser.name)
        assertEquals(response.body!!.username, newUser.username)
        assertEquals(response.body!!.email, newUser.email)
        assertEquals(response.body!!.lastName, newUser.lastName)

        val listResponse = restTemplate.getForEntity<List<UserSerializationDto>>(moduleUrl)

        assertEquals(3, listResponse.body!!.size)
    }

    @Test
    fun `should can edit an existent user`() {
        val userData =
            UserDeserializationDto("juan.rmz", "picapapas", "juanramirez@gmail.com", "Juan", "Ramirez")

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val request = HttpEntity(userData, headers)

        val response =
            restTemplate.exchange("$moduleUrl/2", HttpMethod.PUT, request, UserDeserializationDto::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(response.body!!.name, userData.name)
        assertEquals(response.body!!.username, userData.username)
        assertEquals(response.body!!.email, userData.email)
        assertEquals(response.body!!.lastName, userData.lastName)

        val listResponse = restTemplate.getForEntity<List<UserSerializationDto>>(moduleUrl)

        assertEquals(2, listResponse.body!!.size)
    }

    @Test
    fun `should can delete an existent user`() {
        restTemplate.delete("$moduleUrl/2")

        assertThrows<HttpClientErrorException> {
            val response = restTemplate.getForEntity<UserSerializationDto>("$moduleUrl/2")

            assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        }
    }

}