package com.example.archivai.data.auth

import com.example.archivai.data.auth.models.changePassword.ChangePasswordRequest
import com.example.archivai.data.auth.models.changePassword.ChangePasswordResponse
import com.example.archivai.data.auth.models.login.LoginRequest
import com.example.archivai.data.auth.models.login.LoginResponse
import com.example.archivai.data.auth.models.verifyOtp.OtpVerifyRequest
import com.example.archivai.data.auth.models.verifyOtp.OtpVerifyRequestConfirmation
import com.example.archivai.data.auth.models.sendChangePasswordMail.SendChangePasswordMailRequest
import com.example.archivai.data.auth.models.sendChangePasswordMail.SendChangePasswordMailResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {


    @Headers("Content-Type: application/json","accept: */*")
    @POST
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponse

    @Headers("Content-Type: application/json","accept: */*")
    @POST
    suspend fun verifyOtp(@Body otpVerifyRequest: OtpVerifyRequest) : OtpVerifyRequestConfirmation

    @Headers("Content-Type: application/json","accept: */*")
    @POST
    suspend fun sendChangePasswordEmail(@Body sendChangePasswordMailRequest: SendChangePasswordMailRequest) : SendChangePasswordMailResponse


    @Headers("Content-Type: application/json","accept: */*")
    @POST
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest) :ChangePasswordResponse






}