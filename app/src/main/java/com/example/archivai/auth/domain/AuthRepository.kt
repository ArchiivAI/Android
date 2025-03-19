package com.example.archivai.auth.domain

import com.example.archivai.auth.data.models.changePassword.ChangePasswordRequest
import com.example.archivai.auth.data.models.login.LoginRequest
import com.example.archivai.auth.data.models.sendChangePasswordMail.SendChangePasswordMailRequest
import com.example.archivai.auth.data.models.verifyOtp.OtpVerifyRequest

interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest) :String

    suspend fun sendChangePasswordMail(sendChangePasswordMailRequest: SendChangePasswordMailRequest):String

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) : String

    suspend fun verifyOtp(otpVerifyRequest: OtpVerifyRequest) : String



}