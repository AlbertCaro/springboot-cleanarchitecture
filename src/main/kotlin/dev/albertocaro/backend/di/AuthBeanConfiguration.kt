package dev.albertocaro.backend.di

import dev.albertocaro.backend.core.AuthService
import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.usecases.auth.GetAuthenticatedUser
import dev.albertocaro.backend.domain.usecases.auth.Login
import dev.albertocaro.backend.domain.usecases.user.GetUserByUsername
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthBeanConfiguration {
    @Bean
    fun login(authService: AuthService, userRepository: UserRepository) = Login(authService, userRepository)

    @Bean
    fun getAuthUser(authService: AuthService, getUserByUsername: GetUserByUsername) = GetAuthenticatedUser(authService, getUserByUsername)
}