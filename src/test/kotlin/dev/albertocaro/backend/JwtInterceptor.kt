package dev.albertocaro.backend

import dev.albertocaro.backend.core.AuthService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Service

@Service
class JwtInterceptor(
    private val authService: AuthService
) : ClientHttpRequestInterceptor
{
    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        val token = authService.generateToken("alberto.caro")

        val headers = request.headers
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer $token")
        return execution.execute(request, body)
    }
}