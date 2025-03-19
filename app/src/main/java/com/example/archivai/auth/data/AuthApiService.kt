package com.example.archivai.auth.data

import com.example.archivai.auth.data.models.changePassword.ChangePasswordRequest
import com.example.archivai.auth.data.models.changePassword.ChangePasswordResponse
import com.example.archivai.auth.data.models.login.LoginRequest
import com.example.archivai.auth.data.models.login.LoginResponse
import com.example.archivai.auth.data.models.sendChangePasswordMail.SendChangePasswordMailRequest
import com.example.archivai.auth.data.models.sendChangePasswordMail.SendChangePasswordMailResponse
import com.example.archivai.auth.data.models.verifyOtp.OtpVerifyRequest
import com.example.archivai.auth.data.models.verifyOtp.OtpVerifyRequestConfirmation
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
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest) : ChangePasswordResponse






}