package com.example.archivai.data.repository.auth

import com.example.archivai.data.source.remote.endpoint.auth.AuthApiService
import com.example.archivai.domain.repository.auth.AuthRepository
import com.example.archivai.data.source.remote.requestModels.auth.ChangePasswordRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.LoginRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.OtpVerifyRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.SendChangePasswordMailRequestModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val authApiService: AuthApiService

)  : AuthRepository {
    override suspend fun login(loginRequestModel: LoginRequestModel): String {
        return authApiService.login(loginRequestModel).token
    }

    override suspend fun sendChangePasswordMail(sendChangePasswordMailRequestModel: SendChangePasswordMailRequestModel): String {
        return authApiService.sendChangePasswordEmail(sendChangePasswordMailRequestModel).message
    }

    override suspend fun changePassword(changePasswordRequestModel: ChangePasswordRequestModel): String {
        return authApiService.changePassword(changePasswordRequestModel).message
    }

    override suspend fun verifyOtp(otpVerifyRequestModel: OtpVerifyRequestModel): String {
        return authApiService.verifyOtp(otpVerifyRequestModel).message
    }




}