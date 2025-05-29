package com.example.archivai.domain.usecases.authentication

import com.example.archivai.domain.repository.auth.AuthRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(private val repository  : AuthRepository) {
    suspend operator fun invoke(otp : String , email : String){
         repository.verifyOtp(otp, email)
    }


}