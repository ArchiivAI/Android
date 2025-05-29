package com.example.archivai.domain.usecases.authentication

import com.example.archivai.domain.models.auth.LoginResponse
import com.example.archivai.domain.repository.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<LoginResponse> {
        return authRepository.login(username, password).map { it.toLoginResponse() }
    }
}
