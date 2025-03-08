package com.example.archivai.data.auth.repository

import com.example.archivai.data.auth.AuthApiService
import com.example.archivai.data.auth.models.changePassword.ChangePasswordRequest
import com.example.archivai.data.auth.models.login.LoginRequest
import com.example.archivai.data.auth.models.sendChangePasswordMail.SendChangePasswordMailRequest
import com.example.archivai.data.auth.models.verifyOtp.OtpVerifyRequest
import com.example.archivai.domain.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val authApiService: AuthApiService

)  : AuthRepository{
    override suspend fun login(loginRequest: LoginRequest): String {
        return authApiService.login(loginRequest).token
    }

    override suspend fun sendChangePasswordMail(sendChangePasswordMailRequest: SendChangePasswordMailRequest): String {
        return authApiService.sendChangePasswordEmail(sendChangePasswordMailRequest).message
    }

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): String {
        return authApiService.changePassword(changePasswordRequest).message
    }

    override suspend fun verifyOtp(otpVerifyRequest: OtpVerifyRequest): String {
        return authApiService.verifyOtp(otpVerifyRequest).message
    }




}