package com.example.archivai.auth.data.repository

import com.example.archivai.auth.data.AuthApiService
import com.example.archivai.auth.data.models.changePassword.ChangePasswordRequest
import com.example.archivai.auth.data.models.login.LoginRequest
import com.example.archivai.auth.data.models.sendChangePasswordMail.SendChangePasswordMailRequest
import com.example.archivai.auth.data.models.verifyOtp.OtpVerifyRequest
import com.example.archivai.auth.domain.AuthRepository
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