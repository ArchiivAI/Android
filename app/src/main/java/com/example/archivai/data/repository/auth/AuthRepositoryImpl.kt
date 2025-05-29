package com.example.archivai.data.repository.auth

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.platform.LocalContext
import com.example.archivai.data.source.remote.endpoint.auth.AuthApiService
import com.example.archivai.domain.repository.auth.AuthRepository
import com.example.archivai.data.source.remote.requestModels.auth.ChangePasswordRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.LoginRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.OtpVerifyRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.SendChangePasswordMailRequestModel
import com.example.archivai.data.source.remote.responseModels.auth.LoginResponseModel
import com.example.archivai.data.utils.SharedPrefsHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val authApiService: AuthApiService,
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponseModel> {
        try {
            val loginRequestModel = LoginRequestModel(email, password)
            val response = authApiService.login(loginRequestModel)
            if (response.token.isNotBlank()) {
                SharedPrefsHelper.saveLoginState(response.token)
               return Result.success(response)
            } else {
               return Result.failure(Exception("Invalid or empty token received"))
            }
        } catch (e: retrofit2.HttpException) {
           return Result.failure(Exception("Login failed: ${e.message()}"))
        } catch (e: java.net.UnknownHostException) {
            return Result.failure(Exception("No internet connection"))
        } catch (e: Exception) {
          return  Result.failure(Exception("An error occurred: ${e.message}"))
        }
    }


    override suspend fun sendChangePasswordMail(email: String): Result<Unit> {
        try {
            val sendChangePasswordMailRequestModel = SendChangePasswordMailRequestModel(email)
            val response = authApiService.sendChangePasswordEmail(sendChangePasswordMailRequestModel)
            return if (response.message.contains("Email sent successfully")){
                Result.success(Unit)
            }else{
                Result.failure(Exception("Failed to send change password email"))
            }

        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun changePassword(newPassword: String, email : String, otp: String): Result<Unit> {
        return try {
            val changePasswordRequestModel = ChangePasswordRequestModel(newPassword, email, otp)
            val response = authApiService.changePassword(changePasswordRequestModel)
            if (response.message.contains("Password changed successfully")){
                Result.success(Unit)
            }else{
                Result.failure(Exception("Failed to change password"))
            }

        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun verifyOtp(otpCode: String, email: String): Result<Unit> {
        return try {
            val otpVerifyRequestModel = OtpVerifyRequestModel(otpCode, email)
            val response = authApiService.verifyOtp(otpVerifyRequestModel)
            if(response.message.contains("OTP is correct")){
                Result.success(Unit)
            }else{
                Result.failure(Exception("OTP verification failed"))
            }

        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun contactUS(email: String): Result<Unit> {
        try {
            val response = authApiService.contactUs(email)
            return if (response.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to send contact us email"))
            }

        } catch (e: Exception) {
            return Result.failure(e)
        }

    }


}