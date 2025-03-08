package com.example.archivai.domain.auth

import com.example.archivai.data.auth.models.changePassword.ChangePasswordRequest
import com.example.archivai.data.auth.models.login.LoginRequest
import com.example.archivai.data.auth.models.sendChangePasswordMail.SendChangePasswordMailRequest
import com.example.archivai.data.auth.models.verifyOtp.OtpVerifyRequest

interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest) :String

    suspend fun sendChangePasswordMail(sendChangePasswordMailRequest: SendChangePasswordMailRequest):String

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) : String

    suspend fun verifyOtp(otpVerifyRequest: OtpVerifyRequest) : String



}