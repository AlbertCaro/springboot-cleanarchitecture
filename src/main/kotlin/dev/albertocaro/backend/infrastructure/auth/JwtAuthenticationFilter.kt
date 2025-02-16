package dev.albertocaro.backend.infrastructure.auth

import dev.albertocaro.backend.core.AuthService
import dev.albertocaro.backend.core.AuthService.Companion.SECRET_KEY
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.crypto.spec.SecretKeySpec

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)
        try {
            val claims = validateToken(token)

            val username = claims.subject

            val authentication = UsernamePasswordAuthenticationToken(username, null, null)
            SecurityContextHolder.getContext().authentication = authentication

        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
        }

        filterChain.doFilter(request, response)
    }

    private fun validateToken(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(SecretKeySpec(SECRET_KEY.toByteArray(), "HmacSHA256"))
            .build()
            .parseSignedClaims(token)
            .payload
    }

}