package com.example.archivai.data.source.remote.requestModels.auth

data class ChangePasswordRequestModel(
    val email : String,
    val otp : String,
    val newPassword : String

)
