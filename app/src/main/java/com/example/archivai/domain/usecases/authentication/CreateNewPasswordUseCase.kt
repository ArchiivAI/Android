package com.example.archivai.domain.usecases.authentication

import com.example.archivai.domain.repository.auth.AuthRepository
import javax.inject.Inject

class CreateNewPasswordUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(
        newPassword: String, email: String, otp: String
    ) = repository.changePassword(newPassword, email, otp)

}