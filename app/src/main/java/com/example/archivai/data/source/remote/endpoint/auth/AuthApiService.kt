package com.example.archivai.data.source.remote.endpoint.auth

import com.example.archivai.data.source.remote.requestModels.auth.ChangePasswordRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.LoginRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.OtpVerifyRequestModel
import com.example.archivai.data.source.remote.requestModels.auth.SendChangePasswordMailRequestModel
import com.example.archivai.data.source.remote.responseModels.auth.ChangePasswordResponseModel
import com.example.archivai.data.source.remote.responseModels.auth.LoginResponseModel
import com.example.archivai.data.source.remote.responseModels.auth.OtpVerifyResponseModel
import com.example.archivai.data.source.remote.responseModels.auth.SendChangePasswordMailResponseModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {


    @Headers("Content-Type: application/json","accept: */*")
    @POST("/api/Auth/login")
    suspend fun login(@Body loginRequestModel: LoginRequestModel) : LoginResponseModel

    @Headers("Content-Type: application/json","accept: */*")
    @POST("/api/Auth/verify-otp")
    suspend fun verifyOtp(
        @Body otpVerifyRequestModel: OtpVerifyRequestModel
    ) : OtpVerifyResponseModel

    @Headers("Content-Type: application/json","accept: */*")
    @POST("/api/Auth/send-change-Password-mail")
    suspend fun sendChangePasswordEmail(@Body sendChangePasswordMailRequestModel: SendChangePasswordMailRequestModel) : SendChangePasswordMailResponseModel


    @Headers("Content-Type: application/json","accept: */*")
    @POST("/api/Auth/change-password")
    suspend fun changePassword(@Body changePasswordRequestModel: ChangePasswordRequestModel) : ChangePasswordResponseModel






}