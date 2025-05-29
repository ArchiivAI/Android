package com.example.archivai.domain.repository.auth

import com.example.archivai.data.source.remote.requestModels.auth.ChangePasswordRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.LoginRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.OtpVerifyRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.SendChangePasswordMailRequestModel
import com.example.archivai.data.source.remote.responseModels.auth.LoginResponseModel

interface AuthRepository {

    suspend fun login(email : String , password : String) : Result<LoginResponseModel>

    suspend fun sendChangePasswordMail(email: String): Result<Unit>

    suspend fun changePassword(newPassword : String, email : String , otp: String) : Result<Unit>

    suspend fun verifyOtp(otpCode : String , email : String) : Result<Unit>

    suspend fun contactUS(email : String) : Result<Unit>


}