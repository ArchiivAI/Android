package com.example.archivai.data.source.remote.responseModels.auth

import com.example.archivai.domain.models.auth.LoginResponse

data class LoginResponseModel(
    val token : String
){
    fun toLoginResponse() : LoginResponse{
        return LoginResponse(token)
    }

}
