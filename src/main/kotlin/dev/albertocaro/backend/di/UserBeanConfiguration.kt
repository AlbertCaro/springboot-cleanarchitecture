package dev.albertocaro.backend.di

import dev.albertocaro.backend.core.PasswordEncoderService
import dev.albertocaro.backend.data.UserRepository
import dev.albertocaro.backend.domain.usecases.user.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserBeanConfiguration {

    @Bean
    fun getUserById(repository: UserRepository) = GetUserById(repository)

    @Bean
    fun getUsers(repository: UserRepository) = GetUsers(repository)

    @Bean
    fun createUser(
        repository: UserRepository,
        passwordEncoderService: PasswordEncoderService
    ) =
        CreateUser(repository, passwordEncoderService)

    @Bean
    fun editUser(
        repository: UserRepository,
        getUserById: GetUserById,
        passwordEncoderService: PasswordEncoderService
    ) =
        EditUser(repository, getUserById, passwordEncoderService)

    @Bean
    fun deleteUser(repository: UserRepository) = DeleteUser(repository)
}