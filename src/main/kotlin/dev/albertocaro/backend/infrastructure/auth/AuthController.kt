package dev.albertocaro.backend.infrastructure.auth

import dev.albertocaro.backend.domain.usecases.auth.Login
import dev.albertocaro.backend.infrastructure.auth.model.LoginDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    lateinit var login: Login

    @PostMapping("/login")
    fun executeLogin(@RequestBody loginDto: LoginDto): ResponseEntity<Map<String, String>> {
        val token = login.execute(loginDto.username, loginDto.password)

        if (token != null) {
            return ResponseEntity.ok(mapOf("token" to token))
        }

        return ResponseEntity.status(401).body(mapOf("error" to "Credenciales inválidas"))
    }
}