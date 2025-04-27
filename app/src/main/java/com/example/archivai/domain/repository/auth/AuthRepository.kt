package com.example.archivai.domain.repository.auth

import com.example.archivai.data.source.remote.requestModels.auth.ChangePasswordRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.LoginRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.OtpVerifyRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.SendChangePasswordMailRequestModel

interface AuthRepository {

    suspend fun login(loginRequestModel: LoginRequestModel) :String

    suspend fun sendChangePasswordMail(sendChangePasswordMailRequestModel: SendChangePasswordMailRequestModel):String

    suspend fun changePassword(changePasswordRequestModel: ChangePasswordRequestModel) : String

    suspend fun verifyOtp(otpVerifyRequestModel: OtpVerifyRequestModel) : String



}